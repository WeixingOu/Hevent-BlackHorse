package com.itbh.hevent.mappers;

import com.itbh.hevent.dtos.CategoryDTO;
import com.itbh.hevent.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
}
