package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsFutureValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Future;
import java.lang.annotation.*;
import java.util.Date;

/**
 * @see Future
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsFutureValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsFuture {

    Future value() default @Future;

    Class<?> element() default Date.class;

    String message() default "{validation.collection.constraints.ElementsFuture.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
