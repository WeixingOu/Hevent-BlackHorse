package com.itbh.hevent.dtos.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddCategoryDTO(
    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 3, max = 32, message = "Category name must be between 3 and 32 characters")
    String categoryName,
    @NotBlank(message = "Category alias cannot be blank")
    @Size(min = 3, max = 32, message = "Category alias must be between 3 and 32 characters")
    String categoryAlias
) {
}
