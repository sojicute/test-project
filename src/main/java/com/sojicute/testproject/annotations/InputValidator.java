package com.sojicute.testproject.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class InputValidator implements ConstraintValidator<InputValidation, String>{

    @Override
    public void initialize(InputValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null || s.isEmpty()) {
            return false;
        }

        if (!Pattern.matches("^[1-9]{9}", s)) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        String[] array = s.split("");

        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (i!=j && Objects.equals(array[i], array[j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
