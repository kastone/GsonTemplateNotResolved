package gsontemplatenotresolved

import grails.rest.RestfulController
import grails.validation.Validateable
import org.springframework.http.HttpStatus

class CandidateController extends RestfulController{

    static responseFormats = ['json', 'xml']

    CandidateController() {
        super(Candidate)
    }

    def update(ValidateableCommand command){
        if(command.hasErrors()){
            render (template: "/errorsWithLink", model:[errors: command.errors, link: 'alinkhere'], status: HttpStatus.UNPROCESSABLE_ENTITY)
            //respond (command.errors, [view: "/errorsWithLink"] as Map)//doesn't take template arg so couldn't use.
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

    static constraints = {
        iAmString(nullable:true)
        iAmLong(nullable:true)
    }

    Candidate prepareCandidateForUpdate(Candidate candidate){
        candidate.name = iAmString
        candidate.votes = iAmLong
    }
}
