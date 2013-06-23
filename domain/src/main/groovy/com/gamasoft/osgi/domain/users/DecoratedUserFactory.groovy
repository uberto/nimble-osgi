package com.gamasoft.osgi.domain.users
import com.gamasoft.osgi.domain.talks.Talk
import com.gamasoft.osgi.interfaces.frontend.LinkableResource

class DecoratedUserFactory {

    private Map<String, Talk> talkList

    DecoratedUserFactory(Map<String, Talk> talkList) {
        this.talkList = talkList
    }

    LinkableResource create(User user) {
        def myInterestedTalks = user.schedule.interestedTalkIds.collect { talkList.get(it) }.findAll { it != null }
        new LinkableResource(){
                String resourceName = user.resourceName
                String userName = user.userName
                String email = user.email
                List<Talk> interestedTalks = myInterestedTalks}
    }
}
