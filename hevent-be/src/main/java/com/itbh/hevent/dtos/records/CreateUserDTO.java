package com.itbh.hevent.dtos.records;

import com.itbh.hevent.validations.annotations.UsernameUnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @UsernameUnique
    String username,
    @NotBlank
    @Pattern(regexp = "^\\S{5,16}$", message = "Password must be between 5 and 16 characters without spaces")
    String password
) {
}
