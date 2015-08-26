package org.capsules.validation.collection.constraints.support;

import org.hibernate.validator.metadata.ConstraintHelper;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author Martin Janys
 */
public class CollectionConstraintValidatorSupport implements InitializingBean, ApplicationContextAware {

    private static CollectionConstraintValidatorSupport collectionConstraintValidatorSupport;

    private ConstraintHelper constraintHelper;
    private ConstraintValidatorFactory constraintValidatorFactory;
    private ApplicationContext applicationContext;

    public CollectionConstraintValidatorSupport() {
        this.constraintHelper = new ConstraintHelper();
    }

    public CollectionConstraintValidatorSupport(ConstraintValidatorFactory constraintValidatorFactory) {
        this.constraintHelper = new ConstraintHelper();
        this.constraintValidatorFactory = constraintValidatorFactory;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void afterPropertiesSet() throws Exception {
        if (constraintValidatorFactory == null) {
            constraintValidatorFactory = new SpringConstraintValidatorFactory(applicationContext.getAutowireCapableBeanFactory());
        }
        setCollectionConstraintValidatorSupport(this);
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

    public static void setCollectionConstraintValidatorSupport(CollectionConstraintValidatorSupport collectionConstraintValidatorSupport) {
        CollectionConstraintValidatorSupport.collectionConstraintValidatorSupport = collectionConstraintValidatorSupport;
    }

    public static CollectionConstraintValidatorSupport getCollectionConstraintValidatorSupport() {
        Assert.notNull(CollectionConstraintValidatorSupport.collectionConstraintValidatorSupport, "Initialize helper");
        return CollectionConstraintValidatorSupport.collectionConstraintValidatorSupport;
    }
}

