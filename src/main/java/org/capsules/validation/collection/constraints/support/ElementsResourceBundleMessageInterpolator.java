package org.capsules.validation.collection.constraints.support;

import org.capsules.validation.collection.constraints.ElementsValidator;
import org.capsules.validation.collection.constraints.support.interpolatorcontext.DescriptorAttributesContextWrapper;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Martin Janys
 */
public class ElementsResourceBundleMessageInterpolator extends ResourceBundleMessageInterpolator {

    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_INDEX = "index";
    private static final Pattern INDEXED_PARAMETER_MATCHER = Pattern.compile("(.*)@\\(([\\d,\\s]*)\\)");

    public ElementsResourceBundleMessageInterpolator() {
    }

    public ElementsResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator) {
        super(userResourceBundleLocator);
    }

    public ElementsResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, ResourceBundleLocator contributorResourceBundleLocator) {
        super(userResourceBundleLocator, contributorResourceBundleLocator);
    }

    public ElementsResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, ResourceBundleLocator contributorResourceBundleLocator, boolean cachingEnabled) {
        super(userResourceBundleLocator, contributorResourceBundleLocator, cachingEnabled);
    }

    public ElementsResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, boolean cachingEnabled) {
        super(userResourceBundleLocator, cachingEnabled);
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
        /* find & test elements validator*/
        ConstraintDescriptor<?> constraintDescriptor = context.getConstraintDescriptor();
        Annotation annotation = constraintDescriptor.getAnnotation();
        if (AnnotationUtils.findAnnotation(annotation.annotationType(), ElementsValidator.class) != null) {
            Annotation value = (Annotation) constraintDescriptor.getAttributes().get(ATTRIBUTE_VALUE);
            Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(value, false, true);
            String parsedMessage = parseMessageIndex(message, annotationAttributes);
            return super.interpolate(parsedMessage, new DescriptorAttributesContextWrapper(context, annotationAttributes), locale);
        }
        return super.interpolate(message, context, locale);
    }

    private String parseMessageIndex(String message, Map<String, Object> annotationAttributes) {
        Matcher matcher = INDEXED_PARAMETER_MATCHER.matcher(message);
        if (matcher.matches()) {
            String parsedMessage = matcher.group(1);
            String indexes = matcher.group(2);
            annotationAttributes.put(ATTRIBUTE_INDEX, indexes);
            return parsedMessage;
        }
        else {
            return message;
        }
    }

    public static class HibernateValidatorDelegate {

        public static MessageInterpolator buildMessageInterpolator(MessageSource messageSource) {
            return new ElementsResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource));
        }
    }
}
