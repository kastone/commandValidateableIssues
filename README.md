# commandValidateableIssues

There are inconsistencies in how command objects behave when running unit tests depending on whether the developer manually implements Validateable or relies on Grails to apply
the trait. According to docs here:http://docs.grails.org/3.2.0/guide/single.html#commandObjects

```In this example, the command object class implements the Validateable trait. The Validateable trait allows the definition of Constraints just like in domain classes. If the command object is defined in the same source file as the controller that is using it, Grails will automatically make it Validateable. ```

Inconsistencies around defaultNullable usage with Command Objects inside of Unit tests.
1)When a command object, defined in a controller file, and used as the parameter to an action in the controller, implements Validateable trait manually,
 defaultNullable will be applied correctly in a unit test.

2)Conversely when a command object defined in a controller file, and used as the parameter to an action in the controller,
does not manually implement the Validateable trait, but relies on Grails to apply the trait, you cannot use defaultNullable and if you attempt to do so, you will receive nullable binding errors on each command object field in a unit test.

3)In efforts to upgrade the Grails version before submitting the issue, the unit tests to prove this behavior began failing as the request .json payload was not properly bound to the command objects, regardless of how Validateable was applied.
The run-app behavior remained okay, but the test-app behavior was that the command object properties were null after entering the controller action. the only change was the Grails upgrade from 3.1.10 to 3.2.0.
