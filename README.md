# GsonTemplateNotResolved
Demostrating that the .gson template is not found when running Unit Tests in Grails 3.

CandidateControllerSpec FAILS WITH Unable to load template for uri [/_errorsWithLink]. Template not found.
If you run the app, and issue a PUT request to /candidates/1 you will see the template, _errorsWithLink.gson, is resolved.

The CompositeViewResolver in run-app uses GenericGroovyTemplateView to resolve the template.
In the unit test the GroovyPageViewResolver is the only resolver in the CompositeViewResolver and cannot resolve the template.


