package com.ra.module5_project.validator;

import com.ra.module5_project.repository.NewsRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueNewsTitleValidator implements ConstraintValidator<UniqueNewsTitle, String> {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        return !newsRepository.existsByTitle(s);
    }
}
