package org.capsules.validation.collection.constraints;

import org.springframework.validation.Errors;

/**
 * @author Martin Janys
 */
public class Validator implements org.springframework.validation.Validator {

    public Validator() {}

    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    public void validate(Object target, Errors errors) {
        String value = (String) target;
        if (value.length() != 1) {
            errors.reject("err");
        }
    }
}
