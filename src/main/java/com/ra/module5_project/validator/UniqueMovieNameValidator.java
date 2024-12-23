package com.ra.module5_project.validator;

import com.ra.module5_project.repository.MovieRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueMovieNameValidator implements ConstraintValidator<UniqueMovieName, String> {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public boolean isValid(String movieName, ConstraintValidatorContext constraintValidatorContext) {
        if(movieName == null || movieName.trim().isEmpty()) {
            return true;
        }
        return !movieRepository.existsByMovieName(movieName);
    }
}
