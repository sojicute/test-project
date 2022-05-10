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
@Constraint(validatedBy = TypeValidator.class)
public @interface TypeValidation {

    public String message() default "Неверный тип задачи: должен быть MAGIC_SQUARE или LEXICOGRAPHIC";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
