package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsNull;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsNullValidator
        extends AbstractCollectionValidator<ElementsNull, Collection>
        implements ConstraintValidator<ElementsNull, Collection> {

    public void initialize(ElementsNull constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
