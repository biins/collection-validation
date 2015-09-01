package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Null;
import java.lang.annotation.*;

/**
 * @see Null
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsNullValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ElementsNull {

    Null value() default @Null;

    Class<?> element() default Object.class;

    String message() default "{validation.collection.constraints.ElementsNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
