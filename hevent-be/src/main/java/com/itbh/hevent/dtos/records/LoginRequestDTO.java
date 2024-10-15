package com.itbh.hevent.dtos.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO(
    @NotBlank
    String username,
    @NotBlank
    @Pattern(regexp = "^\\S{5,16}$", message = "Password must be between 5 and 16 characters without spaces")
    String password
) {
}
