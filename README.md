# collection-validation

## Usage

### Java config
Simply add configuration **CollectionValidatorConfig** (don't forget ConfigurationPostProcessor) or
```
// initialize support of collection validation
@Bean
public CollectionConstraintValidatorSupport collectionValidatorSupport() {
  return new CollectionConstraintValidatorSupport();
}
// setup message interpolation
@Bean
public LocalValidatorFactoryBean localValidatorFactoryBean() throws Exception {
  LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
  localValidatorFactoryBean.setMessageInterpolator(messageInterpolation());
  return localValidatorFactoryBean;
}
// get collection validator message interpolator
@Bean
public MessageInterpolator messageInterpolation() throws Exception {
  return messageInterpolationFactoryBean().getObject();
}
// create message interpolator with 
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
```

### XML config
```
<!-- don't forget ConfigurationPostProcessor -->

<bean class="org.capsules.validation.collection.constraints.CollectionValidatorConfig"/>
```
```
<bean class="org.capsules.validation.collection.constraints.support.CollectionConstraintValidatorSupport"/>
       
<bean class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
  <property name="messageInterpolator" ref="collectionValidatorMessageInterpolator"/>
</bean>

<bean id="collectionValidatorMessageInterpolator" class="org.capsules.validation.collection.constraints.support.ElementsMessageInterpolatorFactoryBean">
  <property name="messageSource" ref="collectionValidatorMessageSource"/>
</bean>

<bean id="collectionValidatorMessageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
  <property name="basename" value="CollectionValidationMessages"/>
</bean>
```
