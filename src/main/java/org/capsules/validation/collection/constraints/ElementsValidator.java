package org.capsules.validation.collection.constraints;

import java.lang.annotation.*;

/**
 * @author Martin Janys
 */
@Documented
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementsValidator {
}
