package com.gamasoft.osgi.interfaces.frontend

public interface TalksService {
    def List<LinkableResource> getTalks()

    def LinkableResource getTalkDetails(String talkId)
}
