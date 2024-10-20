package com.itbh.hevent.validations.validators;

import com.itbh.hevent.Repositories.CategoryRepository;
import com.itbh.hevent.validations.annotations.CategoryUnique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public class CategoryUniqueValidator implements ConstraintValidator<CategoryUnique, String> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryUniqueValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(CategoryUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryRepository.existsByCategoryName(categoryName);
    }
}
