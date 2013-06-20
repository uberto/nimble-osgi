package com.gamasoft.osgi.domain.users

import groovy.transform.Canonical

@Canonical
class UserSchedule {
    
    SortedSet<String> interestedTalkIds = [] as SortedSet

}
