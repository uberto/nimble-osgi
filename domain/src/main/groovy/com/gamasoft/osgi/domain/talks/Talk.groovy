package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.transform.Canonical


@Canonical
class Talk implements LinkableResource {
    String resourceName
    String title
    Speaker speaker
    String talkAbstract
    int durationInMin
}
