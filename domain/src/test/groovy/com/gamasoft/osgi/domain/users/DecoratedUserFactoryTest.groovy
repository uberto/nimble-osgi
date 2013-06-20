package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.domain.talks.Talk
import spock.lang.Specification

class DecoratedUserFactoryTest extends Specification {

    def DecoratedUserFactory factory
    def interestTalk = new Talk(resourceName: 'interested', title: 'title')
    def notInterstTalk = new Talk(resourceName: 'notinterested', title: 'notitle')

    def setup() {
        def talks = [interested: interestTalk,
                notinterested: notInterstTalk]
        factory = new DecoratedUserFactory(talks)
    }

    def "decorate user with talks"() {
        given:
        def userPref = new UserSchedule(['notExisting', 'interested'] as SortedSet)
        def user = new User('user', 'userName', 'user@email.com', userPref)

        def DecoratedUser decoratedUser = factory.create(user)
        expect:
        decoratedUser.userName == 'userName'
        decoratedUser.resourceName == 'user'
        decoratedUser.email == 'user@email.com'
        decoratedUser.interestedTalks == [interestTalk]

    }
}
