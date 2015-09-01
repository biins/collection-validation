package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsFuture;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsFutureValidator
        extends AbstractCollectionValidator<ElementsFuture, Collection>
        implements ConstraintValidator<ElementsFuture, Collection> {

    public void initialize(ElementsFuture constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }

}
