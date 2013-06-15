package com.gamasoft.osgi.domain.users

import com.gamasoft.osgi.interfaces.frontend.UserScheduleService
import spock.lang.Specification

class UserScheduleServiceDomainTest extends Specification {
//
//    def "length of Spock's and his friends' names"() {
//        expect:
//        name.size() == length
//
//        where:
//        name     | length
//        "Spock"  | 5
//        "Kirk"   | 4
//        "Scotty" | 6
//    }
//
//    def "maximum of two numbers"() {
//        expect:
//        Math.max(a, b) == c
//
//        where:
//        [a, b, c] << sql.rows("select a, b, c from maxdata")
//    }

    def UserScheduleService service

    def setup(){
        service = new UserScheduleServiceDomain()

    }

    def "create a user"() {
        given:
          def User user = service.createUserPreferences(name, email) as User

        expect:
        user.userName == name
        user.email == email
        user.schedule == new UserSchedule([])
        user.resourceName.startsWith(name)

        where:
        name   |  email
        "adam"  |  "adam@gmail.com"
        "eve"  |  "eve@gmail.com"


    }

    def "retrieve user by resourceName"() {
        given:
        def userId = (service.createUserPreferences(name, email) as User).resourceName

        expect:
//        println "resource name $userId"

        User retrieved = service.getUserSchedule(userId) as User
        retrieved.userName == name
        retrieved.email == email
        retrieved.schedule == new UserSchedule([])
        retrieved.resourceName.startsWith(name)

        where:
        name   |  email
        "adam"  |  "adam@gmail.com"
        "eve"  |  "eve@gmail.com"

    }

    def "cannot retrieve unknown user"() {
        given:
        def adamId = (service.createUserPreferences("adam", "adam@gmail.com") as User).resourceName
        def eveId = (service.createUserPreferences("eve", "eve@gmail.com") as User).resourceName

        expect:
        User adam = service.getUserSchedule(adamId) as User
        User eve = service.getUserSchedule(eveId) as User
        User caio = service.getUserSchedule("caio") as User
        adam.userName == "adam"
        eve.userName == "eve"
        caio == null

    }

    def "add talks to user pref"() {
        given:
        def adamId = (service.createUserPreferences("adam", "adam@gmail.com") as User).resourceName
        def eveId = (service.createUserPreferences("eve", "eve@gmail.com") as User).resourceName
        service.addTalkToUserSchedule(adamId, "talk1")
        service.addTalkToUserSchedule(adamId, "talk2")

        expect:
        User adam = service.getUserSchedule(adamId) as User
        User eve = service.getUserSchedule(eveId) as User
        adam.schedule == new UserSchedule(["talk1", "talk2"])
        eve.schedule.interestedTalkIds.size() == 0

    }

    void testAddTalkToUserSchedule() {

    }

    void testRemoveTalkToUserSchedule() {

    }
}
