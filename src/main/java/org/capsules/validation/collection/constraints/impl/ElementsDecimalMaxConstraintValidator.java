package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsDecimalMax;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsDecimalMaxConstraintValidator
    extends AbstractCollectionValidator<ElementsDecimalMax, Collection>
    implements ConstraintValidator<ElementsDecimalMax, Collection> {

    public void initialize(ElementsDecimalMax constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
