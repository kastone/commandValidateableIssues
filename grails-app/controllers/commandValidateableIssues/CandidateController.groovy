package commandValidateableIssues

import grails.rest.RestfulController
import grails.validation.Validateable

class CandidateController extends RestfulController{

    CandidateService candidateService

    static responseFormats = ['json', 'xml']

    CandidateController() {
        super(Candidate)
    }

    def commandTypeMismatchErrorCorrectlyAppliedWhenGrailsAppliesTrait(ValidateableByTraitCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }else{
            render 'no errors'
        }
    }

    def commandTypeMismatchErrorCorrectlyAppliedWhenTraitManuallyImplemented(ImplementsValidateableCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }else{
            render 'no errors'
        }
    }

    def validateableCommandDefaultNullableWorks(ImplementsValidateableCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }else{
            render "no errors"
        }
    }

    def validateableCommandDefaultNullableDoesNotWork(ValidateableByTraitDefaultNullableCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }else{
            render "no errors"
        }
    }

}

class ValidateableByTraitCommand {
    String iAmString
    Long iAmLong

    static constraints = {
        iAmString(nullable:true)
        iAmLong(nullable:true)
    }
}

class ValidateableByTraitDefaultNullableCommand {
    String iAmString
    Long iAmLong

    //DOES NOT WORK IN UNIT TESTS
    static boolean defaultNullable() {
        true
    }
}

class ImplementsValidateableCommand implements Validateable{
    String iAmString
    Long iAmLong

    static constraints = {
        iAmString(maxSize:100)
    }
//WORKS IN UNIT TESTS
    static boolean defaultNullable() {
        true
    }
}
