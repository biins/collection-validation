package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsDigitsConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import java.lang.annotation.*;

/**
 * @see {@link Digits}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsDigitsConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsDigits {

    Digits value();

    Class<?> element() default Number.class;

    String message() default "{validation.collection.constraints.ElementsDigits[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}