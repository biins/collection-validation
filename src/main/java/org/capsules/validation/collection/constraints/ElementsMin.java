package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsMinValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * @see {@link Min}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsMinValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsMin {

    Min value();

    Class<?> element() default BigDecimal.class;

    String message() default "{validation.collection.constraints.ElementsMin[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
