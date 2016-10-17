package commandValidateableIssues

import grails.test.mixin.*
import spock.lang.*

@TestFor(CandidateController)
@Mock(Candidate)
class CandidateControllerSpec extends Specification {


    void "Test command object that has Validateable applied by Grails catches binding error"() {
        //The command object here manually has implemented Validateable in it's definition

        when: "The udpate action is executed, but the json is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":"abc", "iAmLong":"notReallyLong"}'
        controller.updateValidatableWorks()

        then:
        response.json.toString() == '{"errors":[{"rejected-value":"notReallyLong","field":"iAmLong","message":"Unable to parse number [notReallyLong]","object":"commandValidateableIssues.ValidateableCommand"}]}'
    }

    void "Test command object that manually implements Validateable does not catch binding error"() {
        //The Command object here has a Validateable trait applied at runtime.
        when:"The udpate action is executed, but the json is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":"abc", "iAmLong":"notReallyLong"}'
        controller.updateValidatableDoesNotWork()

        then:
        response.json.toString() == '{"errors":[{"rejected-value":"notReallyLong","field":"iAmLong","message":"Unable to parse number [notReallyLong]","object":"commandValidateableIssues.ValidateableCommand"}]}'

    }
}
