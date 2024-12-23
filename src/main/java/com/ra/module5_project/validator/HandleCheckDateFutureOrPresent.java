package com.ra.module5_project.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HandleCheckDateFutureOrPresent implements ConstraintValidator<CheckDateFutureOrPresent, String> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> classEntity;
    private String field;

    @Override
    public void initialize(CheckDateFutureOrPresent constraintAnnotation) {
        classEntity = constraintAnnotation.entityClass();
        field = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime showDate = LocalDateTime.parse(value);
            LocalDateTime now = LocalDateTime.now();
            return showDate.isAfter(now) || showDate.isEqual(now);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}