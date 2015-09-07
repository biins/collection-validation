package org.biins.validation.collection.constraints;

import org.biins.validation.collection.constraints.support.ElementsMessageInterpolatorFactoryBean;
import org.biins.validation.collection.constraints.support.CollectionConstraintValidatorSupport;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.MessageInterpolator;

/**
 * @author Martin Janys
 */
@Configuration
public class CollectionValidatorConfig {

    /**
     * initialize support of collection validation
     */
    @Bean
    public CollectionConstraintValidatorSupport collectionValidatorSupport() {
        return new CollectionConstraintValidatorSupport();
    }

    /**
     * setup message interpolation
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() throws Exception {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setMessageInterpolator(messageInterpolation());
        return localValidatorFactoryBean;
    }

    /**
     * get collection validator message interpolator
     */
    @Bean
    public MessageInterpolator messageInterpolation() throws Exception {
        return messageInterpolationFactoryBean().getObject();
    }

    /**
     * create message interpolator with
     */
    @Bean
    public ElementsMessageInterpolatorFactoryBean messageInterpolationFactoryBean() {
        ElementsMessageInterpolatorFactoryBean interpolator = new ElementsMessageInterpolatorFactoryBean();
        interpolator.setMessageSource(messageSource());
        return interpolator;
    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("CollectionValidationMessages");
        return messageSource;
    }
}
