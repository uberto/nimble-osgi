package com.gamasoft.osgi.domain.talks
import com.gamasoft.osgi.domain.users.DecoratedUserFactory
import com.gamasoft.osgi.domain.users.User
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.interfaces.persistence.PersistenceService

class TalksServiceDomain implements TalksService {

    def Map<String, Talk> talks = null
    def DecoratedUserFactory decoratedUserFactory
    private Closure<PersistenceService> serviceClosure

    TalksServiceDomain(Closure<PersistenceService> closure) {
        this.serviceClosure = closure
        this.decoratedUserFactory = new DecoratedUserFactory(talks)
    }



    @Override
    List<Talk> getTalks() {

        initTalks()

        println "sending $talks"

        new ArrayList<>(talks.values())
    }

    private initTalks() {
        if (talks == null || talks.isEmpty()) {
            def backendService = serviceClosure()
            if (backendService == null)  {
                println "no backend service "

                talks = [:]
            }
            else {
                def tracks = backendService.loadTracks()
                println "loaded tracks " + tracks.available()

                talks = parseTracks(tracks)
            }
        }
    }

    static Map<String, Talk> parseTracks(InputStream inputStream) {
        println "parsing tracks"
        def talkMap = [:]
        if (inputStream == null)
            return talkMap
        def xml = new XmlSlurper().parse(inputStream)

        xml.talk.each{
            Speaker speaker = new Speaker(resourceName: it.speaker.resourceName, name: it.speaker.name, surname: it.speaker.surname, bio: it.speaker.bio)
            Talk talk = new Talk(resourceName:  it.resourceName, title: it.title, speaker: speaker)
            talkMap.put(talk.resourceName, talk)

        }

        talkMap;
    }


    @Override
    Talk getTalkDetails(String talkId) {
        initTalks()
        talks[talkId]
    }

    @Override
    LinkableResource getUserSchedule(LinkableResource user) {
        return decoratedUserFactory.create(user as User)
    }
}
