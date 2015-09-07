package org.biins.validation.collection.constraints.support.interpolatorcontext.constraintdescriptor;

import javax.validation.ConstraintTarget;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Martin Janys
 */
public class ConstraintDescriptorWrapper<T extends Annotation> implements ConstraintDescriptor<T> {

    protected final ConstraintDescriptor<T> constraintDescriptor;

    public ConstraintDescriptorWrapper(ConstraintDescriptor<T> constraintDescriptor) {
        this.constraintDescriptor = constraintDescriptor;
    }

    public T getAnnotation() {
        return constraintDescriptor.getAnnotation();
    }

    public String getMessageTemplate() {
        return constraintDescriptor.getMessageTemplate();
    }

    public Set<Class<?>> getGroups() {
        return constraintDescriptor.getGroups();
    }

    public Set<Class<? extends Payload>> getPayload() {
        return constraintDescriptor.getPayload();
    }

    public ConstraintTarget getValidationAppliesTo() {
        return constraintDescriptor.getValidationAppliesTo();
    }

    public List<Class<? extends ConstraintValidator<T, ?>>> getConstraintValidatorClasses() {
        return constraintDescriptor.getConstraintValidatorClasses();
    }

    public Map<String, Object> getAttributes() {
        return constraintDescriptor.getAttributes();
    }

    public Set<ConstraintDescriptor<?>> getComposingConstraints() {
        return constraintDescriptor.getComposingConstraints();
    }

    public boolean isReportAsSingleViolation() {
        return constraintDescriptor.isReportAsSingleViolation();
    }
}
