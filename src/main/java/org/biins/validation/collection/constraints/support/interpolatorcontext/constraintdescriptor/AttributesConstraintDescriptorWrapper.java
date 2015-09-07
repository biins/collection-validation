package org.biins.validation.collection.constraints.support.interpolatorcontext.constraintdescriptor;

import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Martin Janys
 */
public class AttributesConstraintDescriptorWrapper<T extends Annotation> extends ConstraintDescriptorWrapper<T> {

    private final Map<String, Object> additionalAttributes;

    public AttributesConstraintDescriptorWrapper(ConstraintDescriptor<T> constraintDescriptor, Map<String, Object> additionalAttributes) {
        super(constraintDescriptor);
        this.additionalAttributes = additionalAttributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(super.getAttributes());
        map.putAll(additionalAttributes);
        return map;
    }
}
