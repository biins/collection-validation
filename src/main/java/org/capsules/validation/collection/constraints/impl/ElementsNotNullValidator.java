package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsNotNull;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsNotNullValidator
        extends CollectionValidatorSupport<ElementsNotNull, Collection>
        implements ConstraintValidator<ElementsNotNull, Collection> {

    public void initialize(ElementsNotNull constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }

}
