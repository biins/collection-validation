package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsURL;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsUrlValidator
        extends AbstractCollectionValidator<ElementsURL, Collection>
        implements ConstraintValidator<ElementsURL, Collection> {

    public void initialize(ElementsURL constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
