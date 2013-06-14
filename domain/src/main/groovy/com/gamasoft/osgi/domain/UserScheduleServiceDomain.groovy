package com.gamasoft.osgi.domain

import com.gamasoft.osgi.domain.values.UserSchedule
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import com.gamasoft.osgi.interfaces.frontend.UserScheduleService

class UserScheduleServiceDomain implements UserScheduleService {

    Random rand = new Random()

    @Override
    LinkableResource createUserPreferences(String userName, String email) {
        return new UserSchedule(userName: userName, resourceName: userName + rand.nextInt(100), email: email)
    }

    @Override
    LinkableResource getUserSchedule(String userId) {
        return new UserSchedule(userName: "john", resourceName: userId, email: "john@google.com")
    }
}
