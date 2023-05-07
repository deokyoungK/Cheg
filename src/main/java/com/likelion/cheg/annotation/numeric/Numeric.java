package com.likelion.cheg.annotation.numeric;

import com.likelion.cheg.annotation.phone.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {NumericValidator.class})
@Documented
public @interface Numeric {

    String message() default "숫자만 허용 가능합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}