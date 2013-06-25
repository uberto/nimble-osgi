package com.gamasoft.osgi.frontend.route

import javax.servlet.http.HttpServletResponse

class RestRoute {
    RestMethod method
    String uri
    Closure call
    String[] routeParts

    RestRoute(RestMethod method, String uri, Closure call) {
        this.method = method
        this.uri = uri
        this.call = call
        this.routeParts = uri.split('/')
    }

    def match(String method, String uri) {
        def noneMatch = [false, [:]]
        if (method != this.method.name())
            return noneMatch;

        def parts = uri.split('/')
        if (routeParts.length != parts.length)
            return noneMatch;

        def params = [:]

        def diff = [routeParts, parts].transpose().find {
            routePart, part ->
                if (routePart.startsWith('$'))
                    params.put(routePart.substring(1), part)
                else if (part != routePart)
                    return true
        }
        if (diff)
            return noneMatch
        return [true, params]
    }


    def process(Map<String, String> params, HttpServletResponse httpServletResponse) {

        call(httpServletResponse, params)

    }
}
