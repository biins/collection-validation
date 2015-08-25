package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.impl.ElementsEmailValidator;
import org.hibernate.validator.constraints.Email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @see {@link Email}
 *
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ElementsEmailValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsEmail {

    Email value() default @Email;

    Class<?> element() default String.class;

    String message() default "{validation.collection.constraints.ElementsEmail[]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
