package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.support.CollectionConstraintValidatorSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author Martin Janys
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCollectionValidator<A extends Annotation, T extends Collection> implements ConstraintValidator<A, T> {

    protected final CollectionConstraintValidatorSupport helper = CollectionConstraintValidatorSupport.getCollectionConstraintValidatorSupport();
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
        List<Integer> indexes = new ArrayList<Integer>();
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            boolean result = validator.isValid(element, context);
            if (!result) {
                indexes.add(i + 1);
                isValid = false;
            }
            i++;
        }
        helper.buildMessage(message, context, indexes);
        return isValid;
    }
}
