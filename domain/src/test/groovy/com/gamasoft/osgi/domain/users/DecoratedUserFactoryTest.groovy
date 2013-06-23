package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.domain.talks.Talk
import spock.lang.Specification

class DecoratedUserFactoryTest extends Specification {

    def interestTalk = new Talk(resourceName: 'interested', title: 'title')
    def notInterstTalk = new Talk(resourceName: 'notinterested', title: 'notitle')

    def talks = [interested: interestTalk, notinterested: notInterstTalk]

    def "decorate user with talks"() {
        given:
        def userPref = new UserSchedule(['notExisting', 'interested'] as SortedSet)
        def user = new User('user', 'userName', 'user@email.com', userPref)

        def decoratedUser = DecoratedUserFactory.create(talks, user)
        expect:
        decoratedUser.userName == 'userName'
        decoratedUser.resourceName == 'user'
        decoratedUser.email == 'user@email.com'
        decoratedUser.interestedTalks == [interestTalk]

    }
}
