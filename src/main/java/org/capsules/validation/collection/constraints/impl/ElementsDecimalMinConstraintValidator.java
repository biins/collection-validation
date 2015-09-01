package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsDecimalMin;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsDecimalMinConstraintValidator
    extends AbstractCollectionValidator<ElementsDecimalMin, Collection>
    implements ConstraintValidator<ElementsDecimalMin, Collection> {

    public void initialize(ElementsDecimalMin constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
