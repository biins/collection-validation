package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsAssertFalseConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.AssertFalse;
import java.lang.annotation.*;

/**
 * @see {@link AssertFalse}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsAssertFalseConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsAssertFalse {

    AssertFalse value() default @AssertFalse;

    Class<?> element() default Boolean.class;

    String message() default "{validation.collection.constraints.ElementsAssertFalse[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
