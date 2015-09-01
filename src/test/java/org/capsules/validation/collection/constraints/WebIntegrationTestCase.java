package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.support.CollectionConstraintValidatorSupport;
import org.capsules.validation.collection.constraints.support.ElementsResourceBundleMessageInterpolator;
import org.capsules.validation.collection.constraints.web.Person;
import org.capsules.validation.collection.constraints.web.PersonController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Martin Janys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = WebIntegrationTestCase.WebConfig.class)
})
public class WebIntegrationTestCase {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    private MockMvc mockMvc;

    @Configuration
    @EnableWebMvc
    static class WebConfig extends WebMvcConfigurerAdapter {
        @Bean
        public PersonController personController() {
            return new PersonController();
        }
        @Bean
        public CollectionConstraintValidatorSupport collectionValidatorSupport() {
            return new CollectionConstraintValidatorSupport();
        }
        @Bean
        public LocalValidatorFactoryBean localValidatorFactoryBean() {
            LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
            localValidatorFactoryBean.setMessageInterpolator(ElementsResourceBundleMessageInterpolator.HibernateValidatorDelegate.buildMessageInterpolator(messageSource()));
            return localValidatorFactoryBean;
        }
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("CollectionValidationMessages");
            return messageSource;
        }
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/persons").param("tags", "A", "BB", "CCC", "DDDD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("person", BindingResult.MODEL_KEY_PREFIX + "person"))
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasErrors("person"))
                .andExpect(model().attributeErrorCount("person", 1))
                .andExpect(model().attributeHasFieldErrors("person", "tags"))
                .andExpect(model().hasErrors());
        /*
        BindingResult bindingResult = (BindingResult) modelAndView.getModel().get(BindingResult.MODEL_KEY_PREFIX + "person");
        String defaultMessage = bindingResult.getFieldError("tags").getDefaultMessage();
        wac.getMessage(bindingResult.getFieldError("tags"), LocaleContextHolder.getLocale());
        MessageInterpolator messageInterpolator = localValidatorFactoryBean.getMessageInterpolator();
        */
    }

    @Test
    public void testMessage() throws Exception {
        Person person = new Person();
        person.setTags(Arrays.asList("A", "BB", "CCC", "DDDD"));
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        Set<ConstraintViolation<Person>> violations = localValidatorFactoryBean.validate(person);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("size must be between 0 and 2 at position 3, 4", new ArrayList<ConstraintViolation>(violations).get(0).getMessage());
    }
}
