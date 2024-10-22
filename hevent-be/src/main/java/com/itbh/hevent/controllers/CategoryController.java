package com.itbh.hevent.controllers;

import com.itbh.hevent.dtos.CategoryDTO;
import com.itbh.hevent.dtos.records.AddCategoryDTO;
import com.itbh.hevent.dtos.records.UpdateCategoryDTO;
import com.itbh.hevent.exceptions.api.ApiResponse;
import com.itbh.hevent.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<ApiResponse<CategoryDTO>> addCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        return new ResponseEntity<>(ApiResponse.success(categoryService.addCategory(addCategoryDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getCategories() {
        return new ResponseEntity<>(ApiResponse.success(categoryService.getCategories()), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable Long categoryId) {
        return new ResponseEntity<>(ApiResponse.success(categoryService.getCategoryById(categoryId)), HttpStatus.OK);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return new ResponseEntity<>(ApiResponse.success(categoryService.updateCategory(categoryId, updateCategoryDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(ApiResponse.success(null), HttpStatus.NO_CONTENT);
    }
}
