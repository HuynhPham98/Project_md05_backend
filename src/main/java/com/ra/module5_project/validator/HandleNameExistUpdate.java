package com.ra.module5_project.validator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HandleNameExistUpdate implements ConstraintValidator<NameExistUpdate,String> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> classEntity ;
    private String field ;
    private String oldName;
    @Override
    public void initialize(NameExistUpdate constraintAnnotation) {
        classEntity = constraintAnnotation.entityClass();
        field = constraintAnnotation.fieldName();
        oldName = constraintAnnotation.oldName();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || !StringUtils.hasText(s.toString())) {
            return true; // Không kiểm tra nếu giá trị null hoặc rỗng
        }

        String query = "SELECT COUNT(e) FROM " + classEntity.getName() + " e WHERE e." + field + " = :value AND e." + field + " != :"+oldName;
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", s)
                .getSingleResult();
        return count == 0 ;
    }

}
