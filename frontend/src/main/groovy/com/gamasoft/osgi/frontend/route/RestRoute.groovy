package com.gamasoft.osgi.frontend.route

import javax.servlet.http.HttpServletResponse

class RestRoute {
    RestMethod method
    String uri
    Closure call
    String[] routParts

    RestRoute(RestMethod method, String uri, Closure call) {
        this.method = method
        this.uri = uri
        this.call = call
        this.routParts = uri.split('/')
    }

    def match(String method, String uri) {
        def noneMatch = [false, [:]]
        def parts = uri.split('/')
        if (routParts.length != parts.length)
            return noneMatch;
        if (method != this.method.name())
            return noneMatch;

        def params = [:]
        for (int i = 0; i < parts.length; i++) {
            def routePart = routParts[i]
            if (routePart.startsWith('$'))
                params.put(routePart.substring(1), parts[i])
            else if (parts[i] != routePart)
                return noneMatch
        }
        return [true, params]
    }

    def process(Map<String, String> params, HttpServletResponse httpServletResponse) {
        call(httpServletResponse, params)

    }
}
