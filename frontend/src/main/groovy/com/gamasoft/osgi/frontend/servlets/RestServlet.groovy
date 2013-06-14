package com.gamasoft.osgi.frontend.servlets

import com.gamasoft.osgi.api.interfaces.TalksService
import com.gamasoft.osgi.frontend.tracker.ServiceProxy

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestServlet extends HttpServlet {

    private final ServiceProxy<TalksService> talksService

    RestServlet(ServiceProxy<TalksService> talksService) {
        this.talksService = talksService
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        def parts = req.requestURI.toString().split('/')

        println " request ${req}"

        resp.writer.write "Welcome to conference ${parts}"


        talksService.call {

            if (parts[2] == "talks") {
                def talks = it.getTalks()

                resp.writer.write "\n\n\n\n ${talks}"
            } else {
                def talkId = parts[3]

                def details = it.getTalkDetails(talkId)

                resp.writer.write "\n\n\n\n ${details}"

            }

        }.orElse {

            resp.writer.write "The service is temporarily unavailable. Please try again later."
        }

//        /conferenceName/talks
//        /conferenceName/talk/talkName
//        /conferenceName/speakers
//        /conferenceName/speaker/speakerName
//        /conferenceName/schedule/yourName <= POST/PUT/DELETE
//        /conferenceName/schedule/yourName/talks


    }
}
