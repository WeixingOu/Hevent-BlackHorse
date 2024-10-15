package com.itbh.hevent.dtos.records;

import com.itbh.hevent.enums.ArticleState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record AddArticleDTO(
    @NotBlank
    @Size(min = 1, max = 30)
    String title,
    @NotBlank
    @Size(min = 1, max = 10000)
    String content,
    @NotBlank
    @URL
    String coverImg,
    @NotNull
    ArticleState state,
    @NotNull
    Long categoryId
) {
}
