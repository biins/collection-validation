package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsPast;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsPastValidator
        extends AbstractCollectionValidator<ElementsPast, Collection>
        implements ConstraintValidator<ElementsPast, Collection> {

    public void initialize(ElementsPast constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }

}
