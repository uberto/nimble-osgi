package com.gamasoft.osgi.interfaces.frontend

public interface UserScheduleService {

    def LinkableResource createUserPreferences(String userName, String email)

    def LinkableResource getUserSchedule(String userId)

    def LinkableResource addTalkToUserSchedule(String userId, String talkId)

    def LinkableResource removeTalkToUserSchedule(String userId, String talkId)

}