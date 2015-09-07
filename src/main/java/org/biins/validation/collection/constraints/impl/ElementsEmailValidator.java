package org.biins.validation.collection.constraints.impl;

import org.biins.validation.collection.constraints.ElementsEmail;

import javax.validation.ConstraintValidator;
import java.util.Collection;

/**
 * @author Martin Janys
 */
public class ElementsEmailValidator
        extends AbstractCollectionValidator<ElementsEmail, Collection>
        implements ConstraintValidator<ElementsEmail, Collection> {

    public void initialize(ElementsEmail constraintAnnotation) {
        initialize(constraintAnnotation.value(), constraintAnnotation.element(), constraintAnnotation.message());
    }
}
