package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService

import java.util.concurrent.ConcurrentHashMap

class UserScheduleServiceDomain implements UserScheduleService {

    Map<String, User> users = new ConcurrentHashMap<>()

    Random rand = new Random()


    @Override
    LinkableResource createUserPreferences(String userName, String email) {
        def emptySchedule = new UserSchedule(interestedTalkIds: [] as SortedSet)
        def user = new User(userName: userName, resourceName: userName + rand.nextInt(100), email: email, schedule: emptySchedule)
        users.put(user.resourceName, user)
        return user
    }

    @Override
    LinkableResource getUserSchedule(String userId) {
        return users.get(userId)
    }

    @Override
    LinkableResource addTalkToUserSchedule(String userId, String talkId) {
        def user = users.get(userId)
        if (user == null)
            return null
        //TODO check if the talkId actually exist
        user.schedule.interestedTalkIds.add(talkId)
        return user
    }

    @Override
    LinkableResource removeTalkToUserSchedule(String userId, String talkId) {
        def user = users.get(userId)
        if (user == null)
            return null
        user.schedule.interestedTalkIds.remove(talkId)
        return user
    }
}
