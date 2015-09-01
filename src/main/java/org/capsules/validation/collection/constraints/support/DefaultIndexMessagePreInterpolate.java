package org.capsules.validation.collection.constraints.support;

import com.google.common.base.Joiner;

import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author Martin Janys
 */
public class DefaultIndexMessagePreInterpolate implements MessagePreInterpolate {

    public void buildMessage(String message, ConstraintValidatorContext context, List<Integer> indexes) {
        context.disableDefaultConstraintViolation();
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate(message + "@(" + Joiner.on(", ").join(indexes) + ")");
        constraintViolationBuilder.addConstraintViolation();
    }
}
