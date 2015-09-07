package org.biins.validation.collection.constraints.support;

import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author Martin Janys
 */
public interface MessagePreInterpolate {
    /**
     *
     * @param message
     * @param context
     * @param indexes starts with 1
     */
    void buildMessage(String message, ConstraintValidatorContext context, List<Integer> indexes);
}
