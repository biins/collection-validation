package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ElementsPastValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Past;
import java.lang.annotation.*;
import java.util.Date;

/**
 * @see Past
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
@ElementsValidator
public @interface ElementsPast {

    Past value() default @Past;

    Class<?> element() default Date.class;

    String message() default "{validation.collection.constraints.ElementsPast.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
