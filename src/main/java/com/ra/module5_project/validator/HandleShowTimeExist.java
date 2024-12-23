package com.ra.module5_project.validator;

import com.ra.module5_project.exception.NoResourceFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class HandleShowTimeExist implements ConstraintValidator<ShowTimeExist, String> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> classEntity;
    private String field;

    @Override
    public void initialize(ShowTimeExist constraintAnnotation) {
        classEntity = constraintAnnotation.entityClass();
        field = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true; // hoặc false, tùy thuộc vào yêu cầu của bạn
        }
        LocalDateTime showDate = null;
        try {
            showDate = LocalDateTime.parse(value);
        }catch (DateTimeParseException e){
            throw new NoResourceFoundException("Invalid date format: " + value);
        }

        String query = "SELECT COUNT(e) FROM " + classEntity.getName() + " e WHERE "
                + "FUNCTION('DATE_FORMAT', e." + field + ", '%Y-%m-%d %H:00:00') = :value"; // MySQL


        // Gán giá trị cho tham số
        LocalDateTime truncatedDate = LocalDateTime.of(showDate.toLocalDate(), showDate.toLocalTime().withMinute(0).withSecond(0).withNano(0));

        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", truncatedDate)
                .getSingleResult();

        return count == 0;
    }
}