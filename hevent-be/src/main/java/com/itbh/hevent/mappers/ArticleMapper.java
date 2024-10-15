package com.itbh.hevent.mappers;

import com.itbh.hevent.dtos.ArticleDTO;
import com.itbh.hevent.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "createUser.id", target = "createUserId")
    ArticleDTO toArticleDTO(Article article);
}
