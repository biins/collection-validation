package org.capsules.validation.collection.constraints.impl;

import org.capsules.validation.collection.constraints.ElementsPattern;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsPatternValidator
        extends AbstractCollectionValidator<ElementsPattern, Collection>
        implements ConstraintValidator<ElementsPattern, Collection> {

    public void initialize(ElementsPattern constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
