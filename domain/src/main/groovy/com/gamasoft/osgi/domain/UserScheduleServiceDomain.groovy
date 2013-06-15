package com.gamasoft.osgi.domain

import com.gamasoft.osgi.domain.values.User
import com.gamasoft.osgi.domain.values.UserSchedule
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService

import java.util.concurrent.ConcurrentHashMap

class UserScheduleServiceDomain implements UserScheduleService {

    Map<String, User> users = new ConcurrentHashMap<>()

    Random rand = new Random()

    def emptySchedule = new UserSchedule(interestedTalkIds: [])

    @Override
    LinkableResource createUserPreferences(String userName, String email) {
        def user = new User(userName: userName, resourceName: userName + rand.nextInt(100), email: email, schedule: emptySchedule)
        users.put(user.resourceName, user)
        return user
    }

    @Override
    LinkableResource getUserSchedule(String userId) {
        return users.get(userId)
    }
}
