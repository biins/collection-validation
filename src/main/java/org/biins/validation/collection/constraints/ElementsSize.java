package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ElementsSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * @see Size
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsSizeValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsSize {

    Size value();

    Class<?> element();

    String message() default "{validation.collection.constraints.ElementsSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
