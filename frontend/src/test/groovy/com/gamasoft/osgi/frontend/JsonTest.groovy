package com.gamasoft.osgi.frontend

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.json.JsonBuilder
import groovy.transform.Canonical

import java.text.SimpleDateFormat

class JsonTest extends GroovyTestCase {

    def jsonBuilder = new JsonBuilder()

    @Canonical
    class MyConf implements LinkableResource {
        String title
        int partecipants
        Date start
        double price
        String resourceName
    }


    void testJsonSerialization() {

        def expensiveConf = new MyConf(resourceName: "bigconf", title: "big conference", partecipants: 1000, start: new SimpleDateFormat("yyyy-MMM-dd").parse("2013-JUN-25"), price: 1500.0)

        def expectedJson = """{"conf":{"start":"2013-06-24T23:00:00+0000","title":"big conference","partecipants":1000,"resourceName":"bigconf","price":1500.0}}"""

        jsonBuilder(conf: expensiveConf)
        def json = jsonBuilder.toString()

        assert json == expectedJson
    }
}


