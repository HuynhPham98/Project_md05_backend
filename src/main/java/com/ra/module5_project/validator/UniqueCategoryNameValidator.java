package com.ra.module5_project.validator;

import com.ra.module5_project.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return true;
        }
        return !categoryRepository.existsByCategoryName(categoryName);
    }
}
