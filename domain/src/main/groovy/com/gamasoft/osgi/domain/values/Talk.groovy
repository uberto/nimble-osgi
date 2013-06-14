package com.gamasoft.osgi.domain.values

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.transform.Canonical


@Canonical
class Talk implements LinkableResource {
    String resourceName
    String title
    String speakerName
    String speakerId
    String talkAbstract
    int durationInMin
}
