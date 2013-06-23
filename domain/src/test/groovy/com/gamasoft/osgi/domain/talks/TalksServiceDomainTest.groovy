package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.interfaces.persistence.PersistenceService
import spock.lang.Specification

class TalksServiceDomainTest extends Specification {

    def PersistenceService backendService = Mock(PersistenceService)

    def TalksServiceDomain service

    def TALKS_XML = """
<talks>
    <talk>
        <resourceName>r1</resourceName>
        <title>t1</title>
        <speaker>
            <resourceName>s1</resourceName>
            <name>sn1</name>
            <surname>ss1</surname>
            <bio>sb1</bio>
        </speaker>
    </talk>
        <talk>
        <resourceName>r2</resourceName>
        <title>t2</title>
        <speaker>
            <resourceName>s2</resourceName>
            <name>sn2</name>
            <surname>ss2</surname>
            <bio>sb2</bio>
        </speaker>
    </talk>
</talks>
"""

    def setup() {

        service = new TalksServiceDomain({ backendService })

    }

    def "retrieve all the tracks"() {
      setup:
        backendService.loadTracks() >> new ByteArrayInputStream(TALKS_XML.bytes)
        def talks = service.getTalks()
        expect:
        talks.size() == 2
    }

    def "retrieve a single track"() {

        setup:
        backendService.loadTracks() >> new ByteArrayInputStream(TALKS_XML.bytes)
        def talk = service.getTalkDetails(talkId)
        expect:
        talk.title == title

        where:
        talkId | title
        "r1"  | "t1"
        "r2" | "t2"

    }

}
