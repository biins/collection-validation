package org.capsules.validation.collection.constraints.spring;

import org.capsules.validation.collection.constraints.impl.spring.ValidElementsConstraintValidator;
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
public @interface ValidElements {

    Class<? extends Validator> value();

    Class<?> element() default Object.class;

    String message() default "{validation.collection.constraints.spring.ValidElements}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
