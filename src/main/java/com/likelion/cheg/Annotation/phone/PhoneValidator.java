package com.likelion.cheg.Annotation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator  implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //10~11길이의 숫자만 허용
        Pattern pattern = Pattern.compile("^[0-9]{10,11}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
