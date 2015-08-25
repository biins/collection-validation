package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsPastValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Past;
import java.lang.annotation.*;
import java.util.Date;

/**
 * @see {@link Past}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsPastValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsPast {

    Past value() default @Past;

    Class<?> element() default Date.class;

    String message() default "{validation.collection.constraints.ElementsPast[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
