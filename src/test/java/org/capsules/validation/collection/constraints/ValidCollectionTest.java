package org.capsules.validation.collection.constraints;

import org.capsules.validation.collection.constraints.support.CollectionConstraintValidatorSupport;
import org.capsules.validation.collection.constraints.spring.ValidElements;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Martin Janys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ValidCollectionTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Configuration
    static class ContextConfiguration {
        @Bean
        public LocalValidatorFactoryBean localValidatorFactoryBean() {
            return new LocalValidatorFactoryBean();
        }
        @Bean
        public CollectionConstraintValidatorSupport constraintValidatorHelper() {
            return new CollectionConstraintValidatorSupport();
        }
    }

    class ValidSize {
        public ValidSize(String... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsSize(element = String.class, value = @Size(max = 2))
        private List<String> field;
    }

    @Test
    public void validSize() {
        ValidSize validSize = new ValidSize();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(0, result.getErrorCount());

        validSize = new ValidSize("1", "1", "1");
        result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(0, result.getErrorCount());

        validSize = new ValidSize("1", "1", "333");
        result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsSize.validSize.field"));
        assertTrue(errorCodes.contains("ElementsSize.field"));
        assertTrue(errorCodes.contains("ElementsSize"));
    }

    @Test
    public void validSizeMulti() {
        ValidSize validSize = new ValidSize();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(0, result.getErrorCount());

        validSize = new ValidSize("333", "1", "333");
        result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(2, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        List<FieldError> fieldErrors = result.getFieldErrors("field");
        List<String> messages = new ArrayList<String>();
        for (FieldError error : fieldErrors) {
            messages.add(error.getDefaultMessage());
        }
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsSize.validSize.field"));
        assertTrue(errorCodes.contains("ElementsSize.field"));
        assertTrue(errorCodes.contains("ElementsSize"));
        assertTrue(messages.contains("{validation.collection.constraints.ElementsSize[0]}"));
        assertTrue(messages.contains("{validation.collection.constraints.ElementsSize[2]}"));
    }

    class ValidSizeCustomMessage {
        public ValidSizeCustomMessage(String... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsSize(element = String.class, value = @Size(max = 2), message = "ElementsSize[]")
        private List<String> field;
    }

    @Test
    public void validSizeCustomMessage() {
        ValidSizeCustomMessage validSize = new ValidSizeCustomMessage();

        validSize = new ValidSizeCustomMessage("1", "1", "333");
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validSize, "validSize");
        validator.validate(validSize, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsSize.validSize.field"));
        assertTrue(errorCodes.contains("ElementsSize.field"));
        assertTrue(errorCodes.contains("ElementsSize"));
    }

    class ValidFalse {
        public ValidFalse(Boolean... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsAssertFalse
        private List<Boolean> field;
    }

    @Test
    public void validAssertFalse() {
        ValidFalse validFalse = new ValidFalse();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validFalse, "validFalse");
        validator.validate(validFalse, result);
        assertEquals(0, result.getErrorCount());

        validFalse = new ValidFalse(false, false);
        result = new BeanPropertyBindingResult(validFalse, "validFalse");
        validator.validate(validFalse, result);
        assertEquals(0, result.getErrorCount());

        validFalse = new ValidFalse(false, true, false);
        result = new BeanPropertyBindingResult(validFalse, "validFalse");
        validator.validate(validFalse, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsAssertFalse.validFalse.field"));
        assertTrue(errorCodes.contains("ElementsAssertFalse.field"));
        assertTrue(errorCodes.contains("ElementsAssertFalse"));
    }

    class ValidTrue {
        public ValidTrue(Boolean... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsAssertTrue(element = Boolean.class, value = @AssertTrue)
        private List<Boolean> field;
    }

    @Test
    public void validAssertTrue() {
        ValidTrue validTrue = new ValidTrue();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validTrue, "validTrue");
        validator.validate(validTrue, result);
        assertEquals(0, result.getErrorCount());

        validTrue = new ValidTrue(true, true);
        result = new BeanPropertyBindingResult(validTrue, "validTrue");
        validator.validate(validTrue, result);
        assertEquals(0, result.getErrorCount());

        validTrue = new ValidTrue(true, false, true);
        result = new BeanPropertyBindingResult(validTrue, "validTrue");
        validator.validate(validTrue, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsAssertTrue.validTrue.field"));
        assertTrue(errorCodes.contains("ElementsAssertTrue.field"));
        assertTrue(errorCodes.contains("ElementsAssertTrue"));
    }

    class ValidDecimalMax {
        public ValidDecimalMax(BigDecimal... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsDecimalMax(element = BigDecimal.class, value = @DecimalMax("5"))
        private List<BigDecimal> field;
    }

    @Test
    public void validDecimalMax() {
        ValidDecimalMax validDecimalMax = new ValidDecimalMax();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validDecimalMax, "validDecimalMax");
        validator.validate(validDecimalMax, result);
        assertEquals(0, result.getErrorCount());

        validDecimalMax = new ValidDecimalMax(new BigDecimal("3"));
        result = new BeanPropertyBindingResult(validDecimalMax, "validDecimalMax");
        validator.validate(validDecimalMax, result);
        assertEquals(0, result.getErrorCount());

        validDecimalMax = new ValidDecimalMax(new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6"));
        result = new BeanPropertyBindingResult(validDecimalMax, "validDecimalMax");
        validator.validate(validDecimalMax, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsDecimalMax.validDecimalMax.field"));
        assertTrue(errorCodes.contains("ElementsDecimalMax.field"));
        assertTrue(errorCodes.contains("ElementsDecimalMax"));
    }

    class ValidDecimalMin {
        public ValidDecimalMin(BigDecimal ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsDecimalMin(value = @DecimalMin("5"))
        private List<BigDecimal> field;
    }

    @Test
    public void validDecimalMin() {
        ValidDecimalMin validDecimalMin = new ValidDecimalMin();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validDecimalMin, "validDecimalMin");
        validator.validate(validDecimalMin, result);
        assertEquals(0, result.getErrorCount());

        validDecimalMin = new ValidDecimalMin(new BigDecimal("6"));
        result = new BeanPropertyBindingResult(validDecimalMin, "validDecimalMin");
        validator.validate(validDecimalMin, result);
        assertEquals(0, result.getErrorCount());

        validDecimalMin = new ValidDecimalMin(new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6"));
        result = new BeanPropertyBindingResult(validDecimalMin, "validDecimalMin");
        validator.validate(validDecimalMin, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsDecimalMin.validDecimalMin.field"));
        assertTrue(errorCodes.contains("ElementsDecimalMin.field"));
        assertTrue(errorCodes.contains("ElementsDecimalMin"));
    }

    class ValidDigits {
        public ValidDigits(BigDecimal ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsDigits(value = @Digits(integer = 1, fraction = 0))
        private List<BigDecimal> field;
    }

    @Test
    public void validDigits() {
        ValidDigits validDigits = new ValidDigits();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validDigits, "validDigits");
        validator.validate(validDigits, result);
        assertEquals(0, result.getErrorCount());

        validDigits = new ValidDigits(new BigDecimal("1"));
        result = new BeanPropertyBindingResult(validDigits, "validDigits");
        validator.validate(validDigits, result);
        assertEquals(0, result.getErrorCount());

        validDigits = new ValidDigits(new BigDecimal("1"), new BigDecimal("22"), new BigDecimal("3"));
        result = new BeanPropertyBindingResult(validDigits, "validDigits");
        validator.validate(validDigits, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsDigits.validDigits.field"));
        assertTrue(errorCodes.contains("ElementsDigits.field"));
        assertTrue(errorCodes.contains("ElementsDigits"));
    }

    class ValidFuture {
        public ValidFuture(Date ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsFuture
        private List<Date> field;
    }

    @Test
    public void validFuture() {
        ValidFuture validFuture = new ValidFuture();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validFuture, "validFuture");
        validator.validate(validFuture, result);
        assertEquals(0, result.getErrorCount());

        validFuture = new ValidFuture(new Date(Long.MAX_VALUE));
        result = new BeanPropertyBindingResult(validFuture, "validFuture");
        validator.validate(validFuture, result);
        assertEquals(0, result.getErrorCount());

        validFuture = new ValidFuture(new Date(Long.MAX_VALUE), new Date(Long.MAX_VALUE), new Date(new Date().getTime() - 1));
        result = new BeanPropertyBindingResult(validFuture, "validFuture");
        validator.validate(validFuture, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsFuture.validFuture.field"));
        assertTrue(errorCodes.contains("ElementsFuture.field"));
        assertTrue(errorCodes.contains("ElementsFuture"));
    }

    class ValidPast {
        public ValidPast(Date ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsPast
        private List<Date> field;
    }

    @Test
    public void validPast() {
        ValidPast validPast = new ValidPast();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validPast, "validPast");
        validator.validate(validPast, result);
        assertEquals(0, result.getErrorCount());

        validPast = new ValidPast(new Date(Long.MIN_VALUE));
        result = new BeanPropertyBindingResult(validPast, "validPast");
        validator.validate(validPast, result);
        assertEquals(0, result.getErrorCount());

        validPast = new ValidPast(new Date(Long.MIN_VALUE), new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
        result = new BeanPropertyBindingResult(validPast, "validPast");
        validator.validate(validPast, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsPast.validPast.field"));
        assertTrue(errorCodes.contains("ElementsPast.field"));
        assertTrue(errorCodes.contains("ElementsPast"));
    }

    class ValidMax {
        public ValidMax(BigDecimal ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsMax(@Max(10))
        private List<BigDecimal> field;
    }

    @Test
    public void validMax() {
        ValidMax validMax = new ValidMax();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validMax, "validMax");
        validator.validate(validMax, result);
        assertEquals(0, result.getErrorCount());

        validMax = new ValidMax(new BigDecimal("5"));
        result = new BeanPropertyBindingResult(validMax, "validMax");
        validator.validate(validMax, result);
        assertEquals(0, result.getErrorCount());

        validMax = new ValidMax(new BigDecimal("5"), new BigDecimal("10"), new BigDecimal("15"));
        result = new BeanPropertyBindingResult(validMax, "validMax");
        validator.validate(validMax, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsMax.validMax.field"));
        assertTrue(errorCodes.contains("ElementsMax.field"));
        assertTrue(errorCodes.contains("ElementsMax"));
    }

    class ValidMin {
        public ValidMin(BigDecimal ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsMin(@Min(10))
        private List<BigDecimal> field;
    }

    @Test
    public void validMin() {
        ValidMin validMin = new ValidMin();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validMin, "validMin");
        validator.validate(validMin, result);
        assertEquals(0, result.getErrorCount());

        validMin = new ValidMin(new BigDecimal("10"));
        result = new BeanPropertyBindingResult(validMin, "validMin");
        validator.validate(validMin, result);
        assertEquals(0, result.getErrorCount());

        validMin = new ValidMin(new BigDecimal("5"), new BigDecimal("10"), new BigDecimal("15"));
        result = new BeanPropertyBindingResult(validMin, "validMin");
        validator.validate(validMin, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsMin.validMin.field"));
        assertTrue(errorCodes.contains("ElementsMin.field"));
        assertTrue(errorCodes.contains("ElementsMin"));
    }

    class ValidNotNull {
        public ValidNotNull(Object ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsNotNull
        private List<Object> field;
    }

    @Test
    public void validNotNull() {
        ValidNotNull validNotNull = new ValidNotNull();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(0, result.getErrorCount());

        validNotNull = new ValidNotNull(new Object());
        result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(0, result.getErrorCount());

        validNotNull = new ValidNotNull(new Object(), null, new Object());
        result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsNotNull.validNotNull.field"));
        assertTrue(errorCodes.contains("ElementsNotNull.field"));
        assertTrue(errorCodes.contains("ElementsNotNull"));
    }

    class ValidNull {
        public ValidNull(Object ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsNull
        private List<Object> field;
    }

    @Test
    public void validNull() {
        ValidNull validNotNull = new ValidNull();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(0, result.getErrorCount());

        validNotNull = new ValidNull(null, null);
        result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(0, result.getErrorCount());

        validNotNull = new ValidNull(null, null, new Object());
        result = new BeanPropertyBindingResult(validNotNull, "validNotNull");
        validator.validate(validNotNull, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsNull.validNotNull.field"));
        assertTrue(errorCodes.contains("ElementsNull.field"));
        assertTrue(errorCodes.contains("ElementsNull"));
    }

    class ValidPatter {
        public ValidPatter(String ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsPattern(@Pattern(regexp = "\\d+"))
        private List<String> field;
    }

    @Test
    public void validPattern() {
        ValidPatter validPatter = new ValidPatter();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validPatter, "validPatter");
        validator.validate(validPatter, result);
        assertEquals(0, result.getErrorCount());

        validPatter = new ValidPatter("111", "222");
        result = new BeanPropertyBindingResult(validPatter, "validPatter");
        validator.validate(validPatter, result);
        assertEquals(0, result.getErrorCount());

        validPatter = new ValidPatter("111", "asd", "222");
        result = new BeanPropertyBindingResult(validPatter, "validPatter");
        validator.validate(validPatter, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsPattern.validPatter.field"));
        assertTrue(errorCodes.contains("ElementsPattern.field"));
        assertTrue(errorCodes.contains("ElementsPattern"));
    }

    class ValidUrl {
        public ValidUrl(String ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsURL
        private List<String> field;
    }

    @Test
    public void validUrl() {
        ValidUrl validUrl = new ValidUrl();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validUrl, "validUrl");
        validator.validate(validUrl, result);
        assertEquals(0, result.getErrorCount());

        validUrl = new ValidUrl("https://www.google.cz/", "https://www.google.cz/");
        result = new BeanPropertyBindingResult(validUrl, "validUrl");
        validator.validate(validUrl, result);
        assertEquals(0, result.getErrorCount());

        validUrl = new ValidUrl("https://www.google.cz/", "asd", "https://www.google.cz/");
        result = new BeanPropertyBindingResult(validUrl, "validUrl");
        validator.validate(validUrl, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsURL.validUrl.field"));
        assertTrue(errorCodes.contains("ElementsURL.field"));
        assertTrue(errorCodes.contains("ElementsURL"));
    }

    class ValidEmail {
        public ValidEmail(String ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsEmail
        private List<String> field;
    }

    @Test
    public void validEmail() {
        ValidEmail validEmail = new ValidEmail();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validEmail, "validEmail");
        validator.validate(validEmail, result);
        assertEquals(0, result.getErrorCount());

        validEmail = new ValidEmail("email@google.cz", "email@google.cz");
        result = new BeanPropertyBindingResult(validEmail, "validEmail");
        validator.validate(validEmail, result);
        assertEquals(0, result.getErrorCount());

        validEmail = new ValidEmail("email@google.cz", "asd", "email@google.cz");
        result = new BeanPropertyBindingResult(validEmail, "validEmail");
        validator.validate(validEmail, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsEmail.validEmail.field"));
        assertTrue(errorCodes.contains("ElementsEmail.field"));
        assertTrue(errorCodes.contains("ElementsEmail"));
    }

    class ValidLength {
        public ValidLength(String ... field) {
            this.field = Arrays.asList(field);
        }
        @ElementsLength(@Length(max = 2))
        private List<String> field;
    }

    @Test
    public void validLength() {
        ValidLength validLength = new ValidLength();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(validLength, "validLength");
        validator.validate(validLength, result);
        assertEquals(0, result.getErrorCount());

        validLength = new ValidLength("1", "22");
        result = new BeanPropertyBindingResult(validLength, "validLength");
        validator.validate(validLength, result);
        assertEquals(0, result.getErrorCount());

        validLength = new ValidLength("1", "22", "333");
        result = new BeanPropertyBindingResult(validLength, "validLength");
        validator.validate(validLength, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ElementsLength.validLength.field"));
        assertTrue(errorCodes.contains("ElementsLength.field"));
        assertTrue(errorCodes.contains("ElementsLength"));
    }

    class Valid {
        public Valid(String ... field) {
            this.field = Arrays.asList(field);
        }
        @ValidElements(org.capsules.validation.collection.constraints.Validator.class)
        private List<String> field;
    }

    @Test
    public void valid() {
        Valid valid = new Valid();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(valid, "valid");
        validator.validate(valid, result);
        assertEquals(0, result.getErrorCount());

        valid = new Valid("1", "1");
        result = new BeanPropertyBindingResult(valid, "valid");
        validator.validate(valid, result);
        assertEquals(0, result.getErrorCount());

        valid = new Valid("1", "22", "1");
        result = new BeanPropertyBindingResult(valid, "valid");
        validator.validate(valid, result);
        assertEquals(1, result.getErrorCount());

        FieldError fieldError = result.getFieldError("field");
        assertEquals("field", fieldError.getField());
        List<String> errorCodes = Arrays.asList(fieldError.getCodes());
        assertTrue(errorCodes.contains("ValidElements.valid.field"));
        assertTrue(errorCodes.contains("ValidElements.field"));
        assertTrue(errorCodes.contains("ValidElements"));
    }

}
