import commandValidateableIssues.Candidate

class BootStrap {

    def init = { servletContext ->
        Candidate candidate = new Candidate(name: 'Hillary Donald', votes: 100L).save(failOnError:true, flush:true)
        assert Candidate.count() == 1
    }
    def destroy = {
    }
}
