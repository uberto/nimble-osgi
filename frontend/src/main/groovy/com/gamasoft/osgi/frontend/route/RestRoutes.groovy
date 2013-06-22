package com.gamasoft.osgi.frontend.route

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class RestRoutes {

    List<RestRoute> routes = []

    public void dispatch(HttpServletRequest req, HttpServletResponse resp) {

        def route = routes.find {
            def (match, params) = it.match(req.method, req.requestURI)
            if (match) {
                params.putAll req.parameterMap
                it.process(params, resp)
                return true
            }
            return false // keep looping
        }

        if (route == null){
            resp.sendError(404, "Uri not valid!")
        }
    }


    public void leftShift (RestMethod method, String uri, Closure closure) {
        routes << new RestRoute(method, uri, closure)
    }

}
