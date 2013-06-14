package com.gamasoft.osgi.domain

import com.gamasoft.osgi.api.interfaces.UserScheduleService
import com.gamasoft.osgi.api.values.UserSchedule


class UserScheduleServiceDomain implements UserScheduleService {
    @Override
    def createUserPreferences(UserSchedule user) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    UserSchedule getUserSchedule(String userId) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
