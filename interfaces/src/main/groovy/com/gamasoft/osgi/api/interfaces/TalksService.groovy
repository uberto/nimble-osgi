package com.gamasoft.osgi.api.interfaces

import com.gamasoft.osgi.api.values.Talk


public interface TalksService {
    def List<Talk> getTalks()

    def Talk getTalkDetails(String talkId)
}
