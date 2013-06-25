package com.gamasoft.osgi.frontend.servlets

import com.gamasoft.osgi.frontend.route.RestRoutes
import com.gamasoft.osgi.frontend.tracker.ServiceProxy
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService
import groovy.json.JsonBuilder

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static com.gamasoft.osgi.frontend.route.RestMethod.*

class RestServlet extends HttpServlet {

    private final ServiceProxy<TalksService> talksService
    private final ServiceProxy<UserScheduleService> userScheduleService
    private final RestRoutes routes = new RestRoutes()

    RestServlet(ServiceProxy<TalksService> talksService, ServiceProxy<UserScheduleService> userScheduleService) {
        this.userScheduleService = userScheduleService
        this.talksService = talksService

        //route registration, the first match with req URI will be served, so order matters

        routes << [GET, '/conference/talks', getTalks]
        routes << [GET, '/conference/talk/$talkId', getTalk]
        routes << [GET, '/conference/schedule/$userId/talks', getSchedule]
        routes << [POST, '/conference/schedule', createUser]
        routes << [PUT, '/conference/schedule/$userId/$talkId', addTalkToUserSchedule]
        routes << [DELETE, '/conference/schedule/$userId/$talkId', removeTalkToUserSchedule]

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        routes.dispatch(req, resp)

    }

    private void noService(resp) {
        resp.sendError 500, "The service is temporarily unavailable. Please try again later."
    }

    def getTalks = { resp, params ->

        talksService.call {
            println "calling talks"

            renderResource(it.getTalks(), resp)
        }.orElse { noService(resp) }

    }

    def getTalk = { resp, params ->

        talksService.call {
            println "calling talk"

            renderResource(it.getTalkDetails(params['talkId']), resp)
        }.orElse { noService(resp) }

    }

    def getSchedule = { resp, params ->

        userScheduleService.call {
            println "calling userSchedule"

            def user = it.getUserSchedule(params['userId'])

            if (user == null)
                resp.sendError(404, "Resource not available")
            else
                talksService.call {
                    renderResource(it.getUserSchedule(user), resp)
                }.orElse { noService(resp) }
        }.orElse { noService(resp) }

    }

    def createUser = { resp, params ->

        userScheduleService.call {
            println "calling userSchedule"

            def userName = params['userName']
            def email = params['email']
            if (userName == null || email == null){
                resp.sendError 400 "Invalid data: userName and email params expected!"
                return
            }
            def user = it.createUserPreferences(userName[0], email[0])

            println "created user $user"

            resp.sendRedirect("${user.resourceName}/talks");
            resp.setStatus(201)

        }.orElse { noService(resp) }

    }

    def addTalkToUserSchedule = { resp, params ->

        userScheduleService.call {
            LinkableResource user = it.addTalkToUserSchedule(params['userId'], params['talkId'])

            if (user == null)
                resp.sendError(404, "Resource not available")
            else {
                resp.setStatus 204
                resp.writer.write "User preference successfully updated!"
            }

        }.orElse { noService(resp) }

    }

    def removeTalkToUserSchedule = { resp, params ->

        userScheduleService.call {
            LinkableResource user = it.removeTalkToUserSchedule(params['userId'], params['talkId'])

            if (user == null)
                resp.sendError(404, "Resource not available")
            else {
                resp.setStatus 204
                resp.writer.write "User preference successfully updated!"
            }

        }.orElse { noService(resp) }

    }


    private void renderResource(resource, HttpServletResponse resp) {
        //TODO render according to request content-type
        println "resource got $resource"

        if (resource == null) {
            resp.sendError 404, "Resource not found!"
        } else {
            resp.contentType = "application/json"
            printResourceInJson(resp.writer, resource)
        }
    }



    private static void printResourceInJson(PrintWriter writer, Object resource) {
        def JsonBuilder jsonBuilder = new JsonBuilder()
        jsonBuilder(response: resource)
        writer.write(jsonBuilder.toPrettyString())

    }


}
