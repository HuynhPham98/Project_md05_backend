package com.ra.module5_project.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EvenOrOddValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EvenOrOdd {
    String message() default "Number must be even or odd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isEven() default true; // true nếu kiểm tra số chẵn, false nếu kiểm tra số lẻ
}