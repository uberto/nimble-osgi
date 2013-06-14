package com.gamasoft.osgi.domain

import com.gamasoft.osgi.api.interfaces.TalksService
import com.gamasoft.osgi.api.values.Talk
import org.osgi.util.tracker.ServiceTracker


class TalksServiceDomain implements TalksService {
    private ServiceTracker tracker

    TalksServiceDomain(ServiceTracker tracker) {
        this.tracker = tracker
    }

    @Override
    List<Talk> getTalks() {
        def Talk t1 = new Talk(id: "t1", title:"osgi")
        def Talk t2 = new Talk(id: "t2", title:"groovy")
        def Talk t3 = new Talk(id: "t3", title:"karaf")
        return [t1, t2, t3]
    }

    @Override
    Talk getTalkDetails(String talkId) {
        return new Talk(id: "t1", title:"osgi", speakerName: "uberto", talkAbstract: "bla bla bla")
    }
}
