package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsDigits;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsDigitsConstraintValidator
        extends AbstractCollectionValidator<ElementsDigits, Collection>
        implements ConstraintValidator<ElementsDigits, Collection> {

    public void initialize(ElementsDigits constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
