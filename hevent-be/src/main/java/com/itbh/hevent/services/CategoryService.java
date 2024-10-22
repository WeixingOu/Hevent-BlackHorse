package com.itbh.hevent.services;

import com.itbh.hevent.dtos.CategoryDTO;
import com.itbh.hevent.dtos.records.AddCategoryDTO;
import com.itbh.hevent.dtos.records.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(AddCategoryDTO addCategoryDTO);

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryById(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(Long categoryId);
}
