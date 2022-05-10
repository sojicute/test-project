package com.sojicute.testproject.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InputValidator.class)
public @interface InputValidation {

    public String message() default "Неверный формат : магический квадрат должен иметь 9 уникальных чисел от 1 до 9 включительно";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
