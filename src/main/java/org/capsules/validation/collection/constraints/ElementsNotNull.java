package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @see {@link NotNull}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsNotNullValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsNotNull {

    NotNull value() default @NotNull;

    Class<?> element() default Object.class;

    String message() default "{validation.collection.constraints.ElementsNotNull[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
