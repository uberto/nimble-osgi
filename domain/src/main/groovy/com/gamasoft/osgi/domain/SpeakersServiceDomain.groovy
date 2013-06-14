package com.gamasoft.osgi.domain

import com.gamasoft.osgi.interfaces.frontend.SpeakersService
import com.gamasoft.osgi.domain.values.Speaker


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
