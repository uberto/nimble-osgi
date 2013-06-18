package com.gamasoft.osgi.domain.talks

import org.osgi.util.tracker.ServiceTrackerCustomizer
import spock.lang.Specification

class TalksServiceDomainTest extends Specification {

    def TalksServiceDomain service

    def setup() {

        def tracker = Mock(ServiceTrackerCustomizer)
        service = new TalksServiceDomain(tracker)

    }

    def "retrieve all the tracks"() {

        given:
        def talks = service.getTalks()
        expect:
        talks.size() == 4
    }

    def "retrieve a single track"() {

        given:
        def talk = service.getTalkDetails(talkId)
        expect:
        talk.title == title

        where:
        talkId   |  title
        "tdd"  |  "tdd is forever"
        "osgi"  |  "osgi is the best"

    }
}
