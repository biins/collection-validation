package org.capsules.validation.collection.constraints.impl.util;

import org.hibernate.validator.metadata.ConstraintHelper;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author Martin Janys
 */
public class ConstraintValidatorHelper {

    private static ConstraintValidatorHelper constraintValidatorHelper;

    private ConstraintHelper constraintHelper;
    private ConstraintValidatorFactory constraintValidatorFactory;

    public ConstraintValidatorHelper() {
        this.constraintHelper = new ConstraintHelper();
        setConstraintValidatorHelper(this);
    }

    public ConstraintValidatorHelper(ConstraintValidatorFactory constraintValidatorFactory) {
        this.constraintHelper = new ConstraintHelper();
        this.constraintValidatorFactory = constraintValidatorFactory;
        setConstraintValidatorHelper(this);
    }

    public List<Class<? extends ConstraintValidator<? extends Annotation, ?>>> getBuiltInConstraintClasses(Class<? extends Annotation> annotationClass) {
        return constraintHelper.getBuiltInConstraints(annotationClass);
    }

    public Class<? extends ConstraintValidator<? extends Annotation, ?>> getBuiltInConstraintClass(Class<? extends Annotation> annotationClass, Class<?> type) {
        List<Class<? extends ConstraintValidator<? extends Annotation, ?>>> validators = constraintHelper.getBuiltInConstraints(annotationClass);

        // equals
        for (Class<? extends ConstraintValidator<? extends Annotation, ?>> validator : validators) {
            Class<?> cls = GenericTypeResolver.resolveTypeArguments(validator, ConstraintValidator.class)[1];
            if (cls != null && cls.equals(type)) {
                return validator;
            }
        }
        // assignable
        for (Class<? extends ConstraintValidator<? extends Annotation, ?>> validator : validators) {
            Class<?> cls = GenericTypeResolver.resolveTypeArguments(validator, ConstraintValidator.class)[1];
            if (cls != null && cls.isAssignableFrom(type)) {
                return validator;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public ConstraintValidator<? extends Annotation, Object> getBuiltInConstraint(Annotation annotation, Class<?> type) {
        Class<? extends Annotation> resolveClass = resolveTargetClass(annotation);
        Class<? extends ConstraintValidator<? extends Annotation, ?>> validator = getValidatedBy(resolveClass);
        if (validator == null) {
            validator = getBuiltInConstraintClass(resolveClass, type);
        }
        Assert.notNull(validator, "Unable to find validator for " + annotation + ", element type: " + type);
        ConstraintValidator instance = constraintValidatorFactory.getInstance(validator);
        return instance;
    }

    private Class<? extends ConstraintValidator<? extends Annotation, ?>> getValidatedBy(Class<? extends Annotation> annotation) {
        Constraint constraint = AnnotationUtils.findAnnotation(annotation, Constraint.class);
        Class<? extends ConstraintValidator<?, ?>>[] classes = constraint.validatedBy();
        return classes != null && classes.length > 0 ? classes[0] : null;
    }

    public Class<? extends Annotation> resolveTargetClass(Annotation annotation) {
        Class<?>[] classes = AopProxyUtils.proxiedUserInterfaces(annotation);
        if (classes != null && classes.length > 0) {
            return (Class<? extends Annotation>) classes[0];
        }
        else {
            return annotation.getClass();
        }
    }

    public void initializeValidator(Annotation annotation, ConstraintValidator validator) {
        validator.initialize(annotation);
    }

    public static void setConstraintValidatorHelper(ConstraintValidatorHelper constraintValidatorHelper) {
        ConstraintValidatorHelper.constraintValidatorHelper = constraintValidatorHelper;
    }

    public static ConstraintValidatorHelper getConstraintValidatorHelper() {
        Assert.notNull(ConstraintValidatorHelper.constraintValidatorHelper, "Initialize helper");
        return ConstraintValidatorHelper.constraintValidatorHelper;
    }
}

