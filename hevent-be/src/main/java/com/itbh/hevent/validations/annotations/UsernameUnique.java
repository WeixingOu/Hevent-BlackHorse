package com.itbh.hevent.validations.annotations;

import com.itbh.hevent.validations.validators.UsernameUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UsernameUnique {
    String message() default "This username is already registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
