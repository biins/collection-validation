package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ElementsUrlValidator;
import org.hibernate.validator.constraints.URL;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @see URL
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsUrlValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsURL {

    URL value() default @URL;

    Class<?> element() default String.class;

    String message() default "{validation.collection.constraints.ElementsURL.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
