package com.gamasoft.osgi.frontend

import com.gamasoft.osgi.api.interfaces.TalksService
import org.osgi.util.tracker.ServiceTracker

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpleServlet extends HttpServlet {

    private final ServiceTracker tracker

    SimpleServlet(ServiceTracker tracker) {
        this.tracker = tracker
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        def parts = req.requestURL.toString().split('/')

        println " request ${req}"

        resp.writer.write "Welcome to conference ${parts[1]}"

        TalksService talksService = tracker.service as TalksService

        def talks = talksService.getTalks()

        resp.writer.write "\n\n\n\n ${talks}"


//        /conferenceName/talks
//        /conferenceName/talk/talkName
//        /conferenceName/speakers
//        /conferenceName/speaker/speakerName
//        /conferenceName/schedule/yourName <= POST/PUT/DELETE
//        /conferenceName/schedule/yourName/talks


    }
}
