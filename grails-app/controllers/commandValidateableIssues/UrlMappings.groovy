package commandValidateableIssues

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //        REST ENDPOINTS
        "/candidates"(resources:'candidate')
        //When called in run-app typeMismatch is correctly applied in both cases. And in unit tests.
        "/api/candidates/commandTypeMismatchErrorCorrectlyAppliedWhenGrailsAppliesTrait"(controller: 'candidate', action: 'commandTypeMismatchErrorCorrectlyAppliedWhenGrailsAppliesTrait', method: 'PUT')
        "/api/candidates/commandTypeMismatchErrorCorrectlyAppliedWhenTraitManuallyImplemented"(controller: 'candidate', action: 'commandTypeMismatchErrorCorrectlyAppliedWhenTraitManuallyImplemented', method: 'PUT')
        //This works in run-app but not unit tests.
        "/api/candidates/defaultNullableWorks"(controller: 'candidate', action: 'validateableCommandDefaultNullableWorks', method: 'PUT')
        "/api/candidates/defaultNullableDoesntWork"(controller: 'candidate', action: 'validateableCommandDefaultNullableDoesNotWork', method: 'PUT')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
