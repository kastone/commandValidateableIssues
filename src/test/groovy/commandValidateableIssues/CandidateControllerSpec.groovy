package commandValidateableIssues

import grails.test.mixin.*
import spock.lang.*

@TestFor(CandidateController)
@Mock(Candidate)
class CandidateControllerSpec extends Specification {


    void "Test command object that has Validateable applied by Grails catches binding error"() {

        when: "JSON payload is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":"abc", "iAmLong":"notReallyLong"}'
        controller.commandTypeMismatchErrorCorrectlyAppliedWhenGrailsAppliesTrait()

        then:
        response.json.toString() == '{"errors":[{"rejected-value":"notReallyLong","field":"iAmLong","message":"Unable to parse number [notReallyLong]","object":"commandValidateableIssues.ValidateableByTraitCommand"}]}'
    }

    void "Test command object that manually implements Validateable catches binding error"() {
        //The command object here manually has implemented Validateable in it's definition
        when:"JSON payload is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":"abc", "iAmLong":"notReallyLong"}'
        controller.commandTypeMismatchErrorCorrectlyAppliedWhenTraitManuallyImplemented()

        then:
        response.json.toString() == '{"errors":[{"rejected-value":"notReallyLong","field":"iAmLong","message":"Unable to parse number [notReallyLong]","object":"commandValidateableIssues.ImplementsValidateableCommand"}]}'

    }


    void "Test that implementing Validateable manually picks up defaultNullable"(){

        when:"The udpate action is executed, but the json is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":null, "iAmLong":null}'
        controller.validateableCommandDefaultNullableWorks()

        then:
        response.text == "no errors"
    }

    void "Test that Command object that has Validateable Trait applied by Grails does not pick up defaultNullable"(){

        when:"The udpate action is executed, but the json is incorrectly passing an alphanumeric string to be bound to Long"
        request.contentType = 'application/json'
        request.json = '{"iAmString":null, "iAmLong":null}'
        controller.validateableCommandDefaultNullableDoesNotWork()

        then:
        response.text == "no errors"
    }
}
