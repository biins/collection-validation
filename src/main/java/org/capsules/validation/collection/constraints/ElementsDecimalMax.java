package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsDecimalMaxConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import java.lang.annotation.*;

/**
 * @see {@link DecimalMax}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsDecimalMaxConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsDecimalMax {

    DecimalMax value();

    Class<?> element() default Number.class;

    String message() default "{validation.collection.constraints.ElementsDecimalMax[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
