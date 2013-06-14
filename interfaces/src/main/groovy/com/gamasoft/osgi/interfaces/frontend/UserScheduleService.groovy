package com.gamasoft.osgi.interfaces.frontend

public interface UserScheduleService {

    def LinkableResource createUserPreferences(String userName, String email)

    def LinkableResource getUserSchedule(String userId)

}