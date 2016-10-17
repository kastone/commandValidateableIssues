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

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
