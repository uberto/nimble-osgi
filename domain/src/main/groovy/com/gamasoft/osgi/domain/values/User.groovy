package com.gamasoft.osgi.domain.values

import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.transform.Canonical


@Canonical
class User implements LinkableResource {
    String resourceName
    String userName
    String email
    UserSchedule schedule
}
