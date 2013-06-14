package com.gamasoft.osgi.domain

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
        def Talk t1 = new Talk(resourceName: "t1", title:"osgi")
        def Talk t2 = new Talk(resourceName: "t2", title:"groovy")
        def Talk t3 = new Talk(resourceName: "t3", title:"karaf")
        return [t1, t2, t3]
    }

    @Override
    Talk getTalkDetails(String talkId) {
        return new Talk(resourceName: "t1", title:"osgi", speakerName: "uberto", talkAbstract: "bla bla bla")
    }
}
