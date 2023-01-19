package com.likelion.cheg.annotation.price;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {PriceValidator.class})
@Documented
public @interface Price {

    String message() default "가격 형식이 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
