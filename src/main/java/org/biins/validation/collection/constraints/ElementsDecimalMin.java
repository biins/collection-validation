package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ElementsDecimalMinConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import java.lang.annotation.*;

/**
 * @see DecimalMin
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
@ElementsValidator
public @interface ElementsDecimalMin {

    DecimalMin value();

    Class<?> element() default Number.class;

    String message() default "{validation.collection.constraints.ElementsDecimalMin.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
