package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.impl.ValidElementsConstraintValidator;
import org.springframework.validation.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Martin Janys
 */
@Documented
@Constraint(validatedBy = ValidElementsConstraintValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@ElementsValidator
public @interface ValidElements {

    Class<? extends Validator> value();

    Class<?> element() default Object.class;

    String message() default "{validation.collection.constraints.spring.ValidElements}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
