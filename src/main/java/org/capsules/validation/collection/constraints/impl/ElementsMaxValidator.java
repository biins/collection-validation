package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsMax;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsMaxValidator
        extends AbstractCollectionValidator<ElementsMax, Collection>
        implements ConstraintValidator<ElementsMax, Collection> {

    public void initialize(ElementsMax constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
