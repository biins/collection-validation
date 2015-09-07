package org.biins.validation.collection.constraints.support.interpolatorcontext;

import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * @author Martin Janys
 */
public class ContextWrapper implements MessageInterpolator.Context {

    protected MessageInterpolator.Context context;

    public ContextWrapper(MessageInterpolator.Context context) {
        this.context = context;
    }

    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return context.getConstraintDescriptor();
    }

    public Object getValidatedValue() {
        return context.getValidatedValue();
    }

    public <T> T unwrap(Class<T> type) {
        return context.unwrap(type);
    }
}
