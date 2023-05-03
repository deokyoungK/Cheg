package com.likelion.cheg.annotation.integer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerPatternValidator implements ConstraintValidator<IntegerPattern, Integer> {

    private final String regex = "^[0-9]*$"; // 정규표현식

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null 값은 검사하지 않음
        }
        String strValue = String.valueOf(value);
        return strValue.matches(regex); // 정규표현식 검사
    }
}
