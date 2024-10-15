package com.itbh.hevent.validations.validators;

import com.itbh.hevent.services.AuthenticationFacade;
import com.itbh.hevent.validations.annotations.ReadOnly;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadOnlyValidator implements ConstraintValidator<ReadOnly, String> {
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ReadOnlyValidator(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void initialize(ReadOnly constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return authenticationFacade.getAuthentication().getName().equals(value);
    }
}
