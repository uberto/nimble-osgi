package com.gamasoft.osgi.frontend.route

import spock.lang.Specification

class RestRouteTest extends Specification {

    RestRoute getList
    RestRoute getItem
    RestRoute putItem
    RestRoute deleteItem

    def setup() {
        getList = new RestRoute(RestMethod.GET, '/items', { println it })
        getItem = new RestRoute(RestMethod.GET, '/item/$itemId/view', { println it })
        putItem = new RestRoute(RestMethod.PUT, '/item/$itemId', { println it })
        deleteItem = new RestRoute(RestMethod.DELETE, '/item/$itemId', { println it })

    }

    def "match URIs to getList route"() {
        expect:
        getList.match(method, uri) == [matching, [:]]

        where:
        method |  uri  | matching
        "GET" | "/items" | true
        "POST" | "/items" | false
        "GET" | "/items/123/view" | false
        "GET" | "/items/123" | false
        "GET" | "/item" | false

    }


    def "match URIs to getItem route"() {
        expect:
        getItem.match(method, uri) == [matching,  params]

        where:
        method |  uri  | matching | params
        "GET" | "/item/123/view" | true | [itemId: "123"]
        "GET" | "/item/myitemId/view" | true | [itemId: "myitemId"]
        "PUT" | "/item/123/view" | false | [:]
        "GET" | "/items/123/view" | false | [:]
        "GET" | "/items/123/view/mine" | false | [:]

    }




}
