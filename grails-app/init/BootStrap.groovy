import gsontemplatenotresolved.Candidate

class BootStrap {

    def init = { servletContext ->
        Candidate candidate =  new Candidate(name: "Hillary Donald", votes: 100).save(failOnError:true, flush:true)
        assert Candidate.count() == 1
    }
    def destroy = {
    }
}
