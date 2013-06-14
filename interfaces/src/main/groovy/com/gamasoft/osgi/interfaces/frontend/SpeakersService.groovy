package com.gamasoft.osgi.interfaces.frontend

public interface SpeakersService {

    def List<LinkableResource> getSpeakers()

    def LinkableResource getSpeakerDetails(String speakerId)
}