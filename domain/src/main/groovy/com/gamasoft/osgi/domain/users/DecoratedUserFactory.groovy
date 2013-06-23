package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.domain.talks.Talk
import com.gamasoft.osgi.interfaces.frontend.LinkableResource

class DecoratedUserFactory {

    static LinkableResource create(Map<String, Talk> talksMap, User user) {
        def myInterestedTalks = user.schedule.interestedTalkIds.findAll { talksMap.containsKey(it) }.collect { talksMap.get(it) }
        new LinkableResource() {
            String resourceName = user.resourceName
            String userName = user.userName
            String email = user.email
            List<Talk> interestedTalks = myInterestedTalks
        }
    }
}
