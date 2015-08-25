package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsDecimalMinConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import java.lang.annotation.*;

/**
 * @see {@link DecimalMin}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsDecimalMinConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsDecimalMin {

    DecimalMin value();

    Class<?> element() default Number.class;

    String message() default "{validation.collection.constraints.ElementsDecimalMin[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
