package com.ra.module5_project.validator;
import com.ra.module5_project.validator.EvenOrOdd;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenOrOddValidator implements ConstraintValidator<EvenOrOdd, Integer> {
    private boolean isEven;

    @Override
    public void initialize(EvenOrOdd constraintAnnotation) {
        this.isEven = constraintAnnotation.isEven();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Không kiểm tra nếu giá trị là null (có thể thêm logic nếu cần)
        }
        return isEven ? value % 2 == 0 : value % 2 != 0; // Kiểm tra số chẵn hoặc số lẻ
    }
}