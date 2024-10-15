package com.itbh.hevent.validations.validators;

import com.itbh.hevent.Repositories.UserRepository;
import com.itbh.hevent.validations.annotations.UsernameUnique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {
    private final UserRepository userRepository;
    @Autowired
    public UsernameUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByUsername(username);
    }
}
