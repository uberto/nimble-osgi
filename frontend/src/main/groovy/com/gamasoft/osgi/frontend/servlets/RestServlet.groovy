package com.gamasoft.osgi.frontend.servlets

import com.gamasoft.osgi.frontend.tracker.ServiceProxy
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService
import groovy.json.JsonBuilder

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestServlet extends HttpServlet {

//        /conferenceName/talks
//        /conferenceName/talk/talkName
//        /conferenceName/schedule/yourName      <= POST/PUT/DELETE
//        /conferenceName/schedule/yourName/talks

    private final ServiceProxy<TalksService> talksService
    private ServiceProxy<UserScheduleService> userScheduleService

    RestServlet(ServiceProxy<TalksService> talksService, ServiceProxy<UserScheduleService> userScheduleService) {
        this.userScheduleService = userScheduleService
        this.talksService = talksService
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        def uri = req.requestURI
        def parts = uri.toString().split('/')

        log "serving GET request ${uri}"

        def action = parts[2]

        //TODO get user schedule

        talksService.call {

            if (action == "talks") {

                println "calling talks"

                renderResource(it.getTalks(), resp)

            } else if (action == "talk") {

                def talkId = parts[3]

                renderResource(it.getTalkDetails(talkId), resp)

            } else {
                resp.sendError(404, "Uri not valid!")
            }

        }.orElse {

            resp.sendError 500, "The service is temporarily unavailable. Please try again later."
        }

        log "ending serving GET request ${uri}"
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        servingUserScheduleRequest(req, resp, { service, parts ->

            def LinkableResource user = service.createUserPreferences(req.getParameter("userName"), req.getParameter("email"))

            println "created user $user"

            resp.sendRedirect("schedule/${user.resourceName}/talks");
            resp.setStatus(201)

        })

    }

    private void servingUserScheduleRequest(HttpServletRequest req, HttpServletResponse resp, Closure closure) {
        def uri = req.requestURI

        log "serving ${req.method} request ${uri}"


        def parts = uri.toString().split('/')
        def action = parts[2]

        userScheduleService.call {

            if (action == "schedule") {

                closure(it, parts)

            } else {
                resp.sendError(404, "Uri not valid!")
            }

        }.orElse {

            resp.sendError 500, "The service is temporarily unavailable. Please try again later."
        }

        log "end serving ${req.method} request ${uri}"

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        servingUserScheduleRequest(req, resp, { service, parts ->

            LinkableResource user = service.addTalkToUserSchedule(parts[3], parts[4])

            if (user == null)
                resp.sendError(404, "Resource not available")
            else {
                resp.setStatus 204
                resp.writer.write "User preference successfully updated!"
            }
        })
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servingUserScheduleRequest(req, resp, { service, parts ->

            LinkableResource user = service.removeTalkToUserSchedule(parts[3], parts[4])
            if (user == null)
                resp.sendError(404, "Resource not available")
            else {
                resp.setStatus 204
                resp.writer.write "User preference successfully deleted!"

            }
        })
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



    private void printResourceInJson(PrintWriter writer, Object resource) {
        def JsonBuilder jsonBuilder = new JsonBuilder()
        jsonBuilder(response: resource)
        writer.write(jsonBuilder.toPrettyString())

    }
}
