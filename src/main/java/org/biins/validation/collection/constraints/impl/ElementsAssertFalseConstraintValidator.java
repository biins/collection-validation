package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsAssertFalse;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsAssertFalseConstraintValidator
        extends AbstractCollectionValidator<ElementsAssertFalse, Collection>
        implements ConstraintValidator<ElementsAssertFalse, Collection> {

    public void initialize(ElementsAssertFalse constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
