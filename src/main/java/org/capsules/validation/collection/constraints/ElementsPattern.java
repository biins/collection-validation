package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsPatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * @see {@link Pattern}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsPatternValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsPattern {

    Pattern value();

    Class<?> element() default String.class;

    String message() default "{validation.collection.constraints.ElementsPattern[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
