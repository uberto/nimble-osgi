package com.gamasoft.osgi.api.interfaces

import com.gamasoft.osgi.api.values.UserSchedule

public interface UserScheduleService {

    def createUserPreferences(UserSchedule user)

    def UserSchedule getUserSchedule(String userId)

}