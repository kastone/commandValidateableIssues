# commandValidateableIssues

There is inconsistencies in how command objects behave depending on whether the developer manually implements Validateable or relies on Grails to apply
the trait. According to docs here:http://docs.grails.org/3.2.0/guide/single.html#commandObjects

```In this example, the command object class implements the Validateable trait. The Validateable trait allows the definition of Constraints just like in domain classes. If the command object is defined in the same source file as the controller that is using it, Grails will automatically make it Validateable. ```

There are 3 issues involving Grails command objects.
1)When a command object, defined in a controller file, and used as the parameter to an action in the controller, implements Validateable trait manually, the databinding does not correctly catch a typeMismatch binding.
However the command object will pick up and apply defaultNullable correctly. 

2)Conversely when a command object defined in a controller file, and used as the parameter to an action in the controller, does not manually implement the Validateable trait, but relies on Grails to apply the trait, the typeMismatch binding error is correctly applied. However, you cannot use defaultNullable and if you attempt to do so, you will receive nullable binding errors on each command object field. 

Since it is more important to have the binding validation correctly applied, the workaround is to manually define that each command object property is nullable:true and rely on Grails to apply the trait.

3)Lastly, in efforts to upgrade the Grails version before submitting the issue, the unit tests to prove this behavior began failing as the request .json payload was not properly bound to the command objects, regardless of how Validateable was applied. The run-app behavior remained okay, but the test-app behavior was that the command object properties were null after entering the controller action. the only change was the Grails upgrade from 3.1.10 to 3.2.0.
