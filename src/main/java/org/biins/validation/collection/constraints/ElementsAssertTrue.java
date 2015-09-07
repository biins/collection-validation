package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ElementsAssertTrueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.AssertTrue;
import java.lang.annotation.*;

/**
 * @see AssertTrue
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsAssertTrueConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsAssertTrue {

    AssertTrue value() default @AssertTrue;

    Class<?> element() default Boolean.class;

    String message() default "{validation.collection.constraints.ElementsAssertTrue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
