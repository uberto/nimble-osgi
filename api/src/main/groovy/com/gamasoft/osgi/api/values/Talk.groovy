package com.gamasoft.osgi.api.values

import groovy.transform.Canonical


@Canonical
class Talk {
    String id
    String title
    String speakerName
    String speakerId
    String talkAbstract
    int durationInMin
}
