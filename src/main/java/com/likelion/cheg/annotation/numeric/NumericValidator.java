package com.likelion.cheg.annotation.numeric;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<Numeric, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null 값은 유효하다고 처리
        }
        return value.toString().matches("-?\\d+"); // 정수인지 검사
    }
}