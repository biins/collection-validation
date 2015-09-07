package org.capsules.validation.collection.constraints.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.MessageSource;

import javax.validation.MessageInterpolator;

/**
 * @author Martin Janys
 */
public class ElementsMessageInterpolatorFactoryBean implements FactoryBean<MessageInterpolator> {

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageInterpolator getObject() throws Exception {
        return ElementsResourceBundleMessageInterpolator.HibernateValidatorDelegate.buildMessageInterpolator(messageSource);
    }

    public Class<?> getObjectType() {
        return MessageInterpolator.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
