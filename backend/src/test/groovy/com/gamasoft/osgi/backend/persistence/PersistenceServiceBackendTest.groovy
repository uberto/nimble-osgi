package com.gamasoft.osgi.backend.persistence

import spock.lang.Specification


class PersistenceServiceBackendTest extends Specification {

    def "read resource"() {
        given:
        def service = new PersistenceServiceBackend()

        def talks = new XmlSlurper().parse(service.loadTalks())

        expect:
        talks.talk[0].speaker.name == "Andy"
        talks.talk[2].title == "how karaf saved our day"
        talks.talk[3].speaker.surname == "Vaccari"

    }
}
