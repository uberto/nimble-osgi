package com.gamasoft.osgi.domain

import com.gamasoft.osgi.domain.values.Speaker
import com.gamasoft.osgi.interfaces.frontend.TalksService
import com.gamasoft.osgi.domain.values.Talk
import org.osgi.util.tracker.ServiceTracker


class TalksServiceDomain implements TalksService {
    private ServiceTracker tracker

    TalksServiceDomain(ServiceTracker tracker) {
        this.tracker = tracker
    }

    @Override
    List<Talk> getTalks() {
        def andy = new Speaker(resourceName: "andys", name:"Andy",surname: "Smith", bio: "programmer")
        def frank = new Speaker(resourceName: "frankb", name:"Frank",surname: "Beniek", bio: "scrum master")
        def jon = new Speaker(resourceName: "jonc", name:"Jon",surname: "Cooper", bio: "team leader")
        def ste = new Speaker(resourceName: "stev", name:"Steve",surname: "Valeri", bio: "agile coach")


        def t1 = new Talk(resourceName: "t1", title:"osgi is the best", speaker: jon)
        def t2 = new Talk(resourceName: "t2", title:"groovy rulez", speaker: andy)
        def t3 = new Talk(resourceName: "t3", title:"karaf saved our day", speaker: frank )
        def t4 = new Talk(resourceName: "t4", title:"tdd is forever", speaker: ste )
        return [t1, t2, t3, t4]
    }

    @Override
    Talk getTalkDetails(String talkId) {
        return new Talk(resourceName: "t1", title:"osgi", speakerName: "uberto", talkAbstract: "bla bla bla")
    }
}
