package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsDigits;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsDigitsConstraintValidator
        extends CollectionValidatorSupport<ElementsDigits, Collection>
        implements ConstraintValidator<ElementsDigits, Collection> {

    public void initialize(ElementsDigits constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
