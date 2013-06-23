package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.domain.users.DecoratedUserFactory
import com.gamasoft.osgi.domain.users.User
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.persistence.PersistenceService
import groovy.util.slurpersupport.GPathResult

class TalksServiceDomain implements TalksService {

    private Closure<PersistenceService> serviceClosure

    TalksServiceDomain(Closure<PersistenceService> closure) {
        this.serviceClosure = closure
    }



    @Override
    List<Talk> getTalks() {

        new ArrayList<>(retrieveTalksMap().values())
    }

    private Map<String, Talk> retrieveTalksMap() {
        def backendService = serviceClosure()
        if (backendService == null) {
            println "no backend service "

            return [:]
        } else {
            def talksStream = backendService.loadTalks()
            println "loaded talksStream " + talksStream.available()

            return parseTalksXml(talksStream)
        }
    }


    static Map<String, Talk> parseTalksXml(InputStream inputStream) {
        println "parsing tracks"
        def talkMap = [:]
        if (inputStream == null)
            return talkMap
        def xml = new XmlSlurper().parse(inputStream)

        xml.talk.each {
            Speaker speaker = new Speaker(resourceName: it.speaker.resourceName, name: it.speaker.name, surname: it.speaker.surname, bio: it.speaker.bio)
            Talk talk = new Talk(resourceName: it.resourceName, title: it.title, durationInMin: parseInt(it.durationInMin, 0), talkAbstract: it.talkAbstract, speaker: speaker)
            talkMap.put(talk.resourceName, talk)

        }

        talkMap;
    }

    static int parseInt(GPathResult node, int defaultValue) {
        if (node == null || node.text().isEmpty())
            return defaultValue
        else
            return Integer.parseInt(node.text());
    }

    @Override
    Talk getTalkDetails(String talkId) {
        retrieveTalksMap()[talkId]
    }

    @Override
    LinkableResource getUserSchedule(LinkableResource user) {

        return DecoratedUserFactory.create(retrieveTalksMap(), user as User)
    }

}
