package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsLengthValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @see Length
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsLengthValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsLength {

    Length value();

    Class<?> element() default String.class;

    String message() default "{validation.collection.constraints.ElementsLength.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
