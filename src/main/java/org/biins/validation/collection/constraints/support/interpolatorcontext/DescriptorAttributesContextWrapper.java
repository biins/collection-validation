package org.biins.validation.collection.constraints.support.interpolatorcontext;

import org.biins.validation.collection.constraints.support.interpolatorcontext.constraintdescriptor.AttributesConstraintDescriptorWrapper;

import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Map;

/**
 * @author Martin Janys
 */
public class DescriptorAttributesContextWrapper extends ContextWrapper {

    protected final Map<String, Object> additionalAttributes;

    public DescriptorAttributesContextWrapper(MessageInterpolator.Context context, Map<String, Object> additionalAttributes) {
        super(context);
        this.additionalAttributes = additionalAttributes;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return new AttributesConstraintDescriptorWrapper(context.getConstraintDescriptor(), additionalAttributes);
    }
}
