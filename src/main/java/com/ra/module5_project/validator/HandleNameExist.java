package com.ra.module5_project.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HandleNameExist implements ConstraintValidator<NameExist,String> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> classEntity ;
    private String field ;
    @Override
    public void initialize(NameExist constraintAnnotation) {
        classEntity = constraintAnnotation.entityClass();
        field = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || !StringUtils.hasText(s.toString())) {
            return true; // Không kiểm tra nếu giá trị null hoặc rỗng
        }

        String query = "SELECT COUNT(e) FROM " + classEntity.getName() + " e WHERE e." + field + " = :value";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", s)
                .getSingleResult();
        return count == 0 ;
    }
}
