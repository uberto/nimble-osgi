package com.gamasoft.osgi.api.interfaces

import com.gamasoft.osgi.api.values.Speaker


public interface SpeakersService {

    def List<Speaker> getSpeakers()

    def Speaker getSpeakerDetails(String speakerId)
}