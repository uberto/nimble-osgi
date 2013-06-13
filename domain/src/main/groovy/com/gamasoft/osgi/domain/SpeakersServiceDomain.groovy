package com.gamasoft.osgi.domain

import com.gamasoft.osgi.api.interfaces.SpeakersService
import com.gamasoft.osgi.api.values.Speaker


class SpeakersServiceDomain implements SpeakersService {
    @Override
    List<Speaker> getSpeakers() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Speaker getSpeakerDetails(String speakerId) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
