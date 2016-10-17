package commandValidateableIssues

import grails.transaction.Transactional

@Transactional
class CandidateService {

    @Transactional
    def getCandidate(Long id) {
        return Candidate.get(id)
    }
}
