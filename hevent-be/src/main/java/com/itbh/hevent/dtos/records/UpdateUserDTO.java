package com.itbh.hevent.dtos.records;

import com.itbh.hevent.validations.annotations.ReadOnly;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
    @ReadOnly
    String username,
    @NotBlank
    @Size(min = 1, max = 20, message = "Nickname must be between 1 and 10 characters")
    String nickname,
    @NotBlank
    @Email
    String email
) {
}
