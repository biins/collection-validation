# collection-validation

## Maven
```
<dependency>
    <groupId>org.biins</groupId>
    <artifactId>collection-validation</artifactId>
    <version>1.0</version>
</dependency>
```

## About

collection-validation:
* extends JSR 303 validation on validation of collection of primitives
* support all element types as in javax.validation.constraints 
* support some important hibernate validation
* validation message index is 1 based

## Example
```
--- CLASS ---

class Person {
    @ElementsSize(element = String.class, value = @Size(max = 20))
    private List<String> tags;
}

--- VALIDATION ---

@Valid @ModelAttribute Person person

--- MESSAGE BUNDLE ---

{javax.validation.constraints.Size.message} at position {index}

--- MESSAGE ---

size must be between 0 and 20 at position 1

```

## Simple configuration

### Java config
```
@Import(org.biins.validation.collection.constraints.CollectionValidatorConfig)
```
### XML config
* don't forget ConfigurationPostProcessor
```
<bean class="org.biins.validation.collection.constraints.CollectionValidatorConfig"/>
```

## Supported validations

* 'Collection' validator -> 'Element' validator
* ElementsAssertFalse -> AssertFalse
* ElementsAssertTrue -> AssertTrue
* ElementsDecimalMax -> DecimalMax
* ElementsDecimalMin -> DecimalMin
* ElementsDigits -> Digits
* ElementsEmail -> Email
* ElementsFuture -> Future
* ElementsLength -> Length
* ElementsMax -> Max
* ElementsMin -> Min
* ElementsNotNull -> NotNull
* ElementsNull -> Null
* ElementsPast -> Past
* ElementsPattern -> Pattern
* ElementsSize -> Size
* ElementsURL -> URL
* ValidElements -> Validator (custom Spring validator)


## Complex configuration

### Java config
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
// create message interpolator with message source
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
<!-- initialize support of collection validation -->
<bean class="org.biins.validation.collection.constraints.support.CollectionConstraintValidatorSupport"/>
<!-- setup message interpolation -->
<bean class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
  <property name="messageInterpolator" ref="collectionValidatorMessageInterpolator"/>
</bean>
<!-- create message interpolator with message source -->
<bean id="collectionValidatorMessageInterpolator" class="org.biins.validation.collection.constraints.support.ElementsMessageInterpolatorFactoryBean">
  <property name="messageSource" ref="collectionValidatorMessageSource"/>
</bean>
<!-- create message source -->
<bean id="collectionValidatorMessageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
  <property name="basename" value="CollectionValidationMessages"/>
</bean>
```
