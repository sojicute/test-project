package com.sojicute.testproject.annotations;

import com.sojicute.testproject.domain.Type;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class TypeValidator implements ConstraintValidator<TypeValidation, Type> {

    @Override
    public void initialize(TypeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Type type, ConstraintValidatorContext constraintValidatorContext) {
        List<String> list = Arrays.asList("MAGIC_SQUARE","LEXICOGRAPHIC");
        return list.contains(type.toString());
    }
}
