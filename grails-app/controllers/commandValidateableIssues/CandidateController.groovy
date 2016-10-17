package commandValidateableIssues

import grails.rest.RestfulController
import grails.validation.Validateable

class CandidateController extends RestfulController{

    CandidateService candidateService

    static responseFormats = ['json', 'xml']

    CandidateController() {
        super(Candidate)
    }

    def updateValidatableWorks(ValidateableCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }
        Candidate candidateToUpdate = Candidate.get(params.id)
        candidateToUpdate = command.prepareCandidateForUpdate(candidateToUpdate)
        if(!candidateToUpdate.save()) {
            respond candidateToUpdate.errors
            return
        }else{
            respond candidateToUpdate
            return
        }
    }

    def updateValidatableDoesNotWork(ImplementsValidateableCommand command){
        if(command.hasErrors()){
            respond command.errors
            return
        }
        Candidate candidateToUpdate = Candidate.get(params.id)
        candidateToUpdate = command.prepareCandidateForUpdate(candidateToUpdate)
        if(!candidateToUpdate.save()) {
            respond candidateToUpdate.errors
            return
        }else{
            respond candidateToUpdate
            return
        }
    }

}

class ValidateableCommand{
    String iAmString
    Long iAmLong

    //can't use nullableDefault b/c of Validateable bug.

    static constraints = {
        iAmString(nullable:true)
        iAmLong(nullable:true)
    }

    Candidate prepareCandidateForUpdate(Candidate candidate){
        candidate.name = iAmString
        candidate.votes = iAmLong
    }
}

class ImplementsValidateableCommand implements Validateable{
    String iAmString
    Long iAmLong

    static boolean defaultNullable() {
        true
    }

    Candidate prepareCandidateForUpdate(Candidate candidate){
        candidate.name = iAmString
        candidate.votes = iAmLong
    }
}
