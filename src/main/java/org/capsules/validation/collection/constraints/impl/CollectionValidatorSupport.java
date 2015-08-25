package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.impl.util.ConstraintValidatorHelper;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Martin Janys
 */
@SuppressWarnings("unchecked")
public abstract class CollectionValidatorSupport<A extends Annotation, T extends Collection> implements ConstraintValidator<A, T> {

    protected final ConstraintValidatorHelper helper = ConstraintValidatorHelper.getConstraintValidatorHelper();
    protected ConstraintValidator validator;
    protected String message;

    public void initialize(Annotation constraintAnnotation, Class<?> element, String message) {
        this.validator = helper.getBuiltInConstraint(constraintAnnotation, element);
        this.validator.initialize(constraintAnnotation);
        this.message = message;
    }

    public boolean isValid(Collection elements, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(elements))
            return true;

        boolean isValid = true;
        int i = 0;
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            boolean result = validator.isValid(element, context);
            if (!result) {
                buildMessage(context, element, i);
                isValid = false;
            }
            i++;
        }
        return isValid;
    }

    protected void buildMessage(ConstraintValidatorContext context, Object element, int i) {
        context.disableDefaultConstraintViolation();
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate(message.replace("[]", "["+i+"]"));
        constraintViolationBuilder.addConstraintViolation();
    }
}
