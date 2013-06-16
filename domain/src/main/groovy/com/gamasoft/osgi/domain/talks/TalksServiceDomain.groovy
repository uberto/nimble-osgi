package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.interfaces.frontend.TalksService
import org.osgi.util.tracker.ServiceTracker


class TalksServiceDomain implements TalksService {
    private ServiceTracker tracker

    TalksServiceDomain(ServiceTracker tracker) {
        this.tracker = tracker
    }

    @Override
    List<Talk> getTalks() {
        ArrayList<Talk> talks = retrieveTalks()
        return talks
    }

    private ArrayList<Talk> retrieveTalks() {

       //TODO get from shared map
        def andy = new Speaker(resourceName: "andys", name: "Andy", surname: "Smith", bio: "programmer")
        def frank = new Speaker(resourceName: "frankb", name: "Frank", surname: "Beniek", bio: "scrum master")
        def jon = new Speaker(resourceName: "jonc", name: "Jon", surname: "Cooper", bio: "team leader")
        def ste = new Speaker(resourceName: "stev", name: "Steve", surname: "Valeri", bio: "agile coach")


        def t1 = new Talk(resourceName: "t1", title: "osgi is the best", speaker: jon)
        def t2 = new Talk(resourceName: "t2", title: "groovy rulez", speaker: andy)
        def t3 = new Talk(resourceName: "t3", title: "karaf saved our day", speaker: frank)
        def t4 = new Talk(resourceName: "t4", title: "tdd is forever", speaker: ste)
        def talks = [t1, t2, t3, t4]

        println "sending $talks"

        talks
    }

    @Override
    Talk getTalkDetails(String talkId) {
        def ste = new Speaker(resourceName: "stev", name: "Steve", surname: "Valeri", bio: "agile coach")

        return new Talk(resourceName: "t4", title: "tdd is forever", speaker: ste)
    }
}
