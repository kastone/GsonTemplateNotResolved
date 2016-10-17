package gsontemplatenotresolved

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CandidateController)
@Mock(Candidate)
class CandidateControllerSpec extends Specification {


    void 'test that /_errorsWithLink .gson template is resolved'() {
        given:

        Candidate candidate = new Candidate(name: 'Ken').save(failOnError:true, flush: true)
        assert Candidate.count() == 1
        params.id = candidate.id

        when:
        request.contentType = 'application/json'
        request.method = 'PUT'
        request.json = '{"iAmString":"Ken Bone", "iAmLong":"notReallyLong"}'

        controller.update()

        then:
        response.text.toString().contains('"status":"error","message":"Validation Failed"')
        //FAILS WITH Unable to load template for uri [/_errorsWithLink]. Template not found.
        //If you run the app, you will see the template is resolved okay. The CompositeViewResolver in run-app uses GenericGroovyTemplateView to resolve the template.
        //In the unit test the GroovyPageViewResolver is the only resolver in the CompositeViewResolver and cannot resolve the template.
    }
}
