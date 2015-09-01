package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ValidElements;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Martin Janys
 */
public class ValidElementsConstraintValidator
        implements ConstraintValidator<ValidElements, Collection> {

    protected Validator validator;
    protected String message;

    public void initialize(ValidElements constraintAnnotation) {
        Class<? extends Validator> cls = constraintAnnotation.value();
        validator = BeanUtils.instantiate(cls);
        message = constraintAnnotation.message();
    }

    public boolean isValid(Collection elements, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(elements))
            return true;
        boolean isValid = true;
        int i = 0;
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            DirectFieldBindingResult bindingResult = new DirectFieldBindingResult(elements, "element["+i+"]");
            if (validator.supports(element.getClass())) {
                validator.validate(element, bindingResult);
            }
            if (bindingResult.hasErrors()) {
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
