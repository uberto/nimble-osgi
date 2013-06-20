package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.domain.talks.Talk
import com.gamasoft.osgi.interfaces.frontend.LinkableResource
import groovy.transform.Immutable


@Immutable
class DecoratedUser implements LinkableResource {
    String resourceName
    String userName
    String email
    List<Talk> interestedTalks
}
