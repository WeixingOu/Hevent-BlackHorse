package com.itbh.hevent.dtos.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserPasswordDTO(
    @NotBlank(message = "Current password is required")
    @Pattern(regexp = "^\\S{5,16}$", message = "Password must be between 5 and 16 characters without spaces")
    String currentPassword,
    @NotBlank(message = "New password is required")
    @Pattern(regexp = "^\\S{5,16}$", message = "Password must be between 5 and 16 characters without spaces")
    String newPassword,
    @NotBlank(message = "CNew password confirmation is required")
    @Pattern(regexp = "^\\S{5,16}$", message = "Password must be between 5 and 16 characters without spaces")
    String rePassword
) {
}
