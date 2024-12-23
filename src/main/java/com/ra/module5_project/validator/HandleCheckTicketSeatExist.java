package com.ra.module5_project.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.List;

public class HandleCheckTicketSeatExist implements ConstraintValidator<CheckTicketSeatExist, Long> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> classEntity ;
    private String field ;
    @Override
    public void initialize(CheckTicketSeatExist constraintAnnotation) {
        classEntity = constraintAnnotation.entityClass();
        field = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true; // Không kiểm tra nếu giá trị null hoặc rỗng
        }


       return true ;
    }
}
