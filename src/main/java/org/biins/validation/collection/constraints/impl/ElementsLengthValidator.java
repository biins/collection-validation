package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsLength;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsLengthValidator
        extends AbstractCollectionValidator<ElementsLength, Collection>
        implements ConstraintValidator<ElementsLength, Collection> {

    public void initialize(ElementsLength constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
