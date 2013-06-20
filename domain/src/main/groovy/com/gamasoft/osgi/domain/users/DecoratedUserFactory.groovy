package com.gamasoft.osgi.domain.users
import com.gamasoft.osgi.domain.talks.Talk

class DecoratedUserFactory {

    private Map<String, Talk> talkList

    DecoratedUserFactory(Map<String, Talk> talkList) {
        this.talkList = talkList
    }

    DecoratedUser create(User user) {
        def interestedTalks = user.schedule.interestedTalkIds.collect { talkList.get(it) }.findAll { it != null }
        return new DecoratedUser(resourceName: user.resourceName,
                userName:user.userName,
                email: user.email,
                interestedTalks: interestedTalks)
    }
}
