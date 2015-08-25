package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsMin;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsMinValidator
        extends CollectionValidatorSupport<ElementsMin, Collection>
        implements ConstraintValidator<ElementsMin, Collection> {

    public void initialize(ElementsMin constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
