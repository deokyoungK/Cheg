package com.likelion.cheg.annotation.price;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceValidator  implements ConstraintValidator<Price, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //숫자만 허용
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
