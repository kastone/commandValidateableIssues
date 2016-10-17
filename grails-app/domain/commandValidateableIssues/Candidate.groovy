package commandValidateableIssues

class Candidate {
    String name
    Long votes

    static constraints = {
        name(nullable:true)
        votes(nullable:true)
    }
}
