package com.gamasoft.osgi.domain.talks

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.transform.Canonical


@Canonical
class Speaker implements LinkableResource {
    String resourceName
    String name
    String surname
    String bio
}
