package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.domain.users.DecoratedUserFactory
import com.gamasoft.osgi.domain.users.User
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.TalksService
import org.osgi.util.tracker.ServiceTrackerCustomizer

class TalksServiceDomain implements TalksService {

    def ServiceTrackerCustomizer tracker
    def Map<String, Talk> talks
    def DecoratedUserFactory decoratedUserFactory

    TalksServiceDomain(ServiceTrackerCustomizer tracker) {
        this.tracker = tracker
        this.talks = createTalks()
        this.decoratedUserFactory = new DecoratedUserFactory(talks)
    }



    @Override
    List<Talk> getTalks() {
        println "sending $talks"

        new ArrayList<>(talks.values())
    }

    private static Map<String, Talk> createTalks() {

        def andy = new Speaker(resourceName: "andys", name: "Andy", surname: "Smith", bio: "programmer")
        def frank = new Speaker(resourceName: "frankb", name: "Frank", surname: "Beniek", bio: "scrum master")
        def jon = new Speaker(resourceName: "jonc", name: "Jon", surname: "Cooper", bio: "team leader")
        def ste = new Speaker(resourceName: "stev", name: "Steve", surname: "Valeri", bio: "agile coach")


        def t1 = new Talk(resourceName: "osgi", title: "osgi is the best", speaker: jon)
        def t2 = new Talk(resourceName: "groovy", title: "groovy rulez!!!", speaker: andy)
        def t3 = new Talk(resourceName: "karaf", title: "karaf saved our day", speaker: frank)
        def t4 = new Talk(resourceName: "tdd", title: "tdd is forever", speaker: ste)
        def talks = [osgi: t1, groovy: t2, karaf: t3, tdd: t4]

        talks
    }

    @Override
    Talk getTalkDetails(String talkId) {

        talks[talkId]
    }

    @Override
    LinkableResource getUserSchedule(LinkableResource user) {
        return decoratedUserFactory.create(user as User)
    }
}
