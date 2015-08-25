package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsAssertFalse;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsAssertFalseConstraintValidator
        extends CollectionValidatorSupport<ElementsAssertFalse, Collection>
        implements ConstraintValidator<ElementsAssertFalse, Collection> {

    public void initialize(ElementsAssertFalse constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
