package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsPast;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsPastValidator
        extends CollectionValidatorSupport<ElementsPast, Collection>
        implements ConstraintValidator<ElementsPast, Collection> {

    public void initialize(ElementsPast constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }

}
