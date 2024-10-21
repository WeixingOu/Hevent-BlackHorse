package com.itbh.hevent.services.impls;

import com.itbh.hevent.Repositories.CategoryRepository;
import com.itbh.hevent.Repositories.UserRepository;
import com.itbh.hevent.dtos.CategoryDTO;
import com.itbh.hevent.dtos.records.AddCategoryDTO;
import com.itbh.hevent.dtos.records.UpdateCategoryDTO;
import com.itbh.hevent.mappers.CategoryMapper;
import com.itbh.hevent.models.Category;
import com.itbh.hevent.models.User;
import com.itbh.hevent.services.CategoryService;
import com.itbh.hevent.utils.AuthenticationFacadeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;
    private final AuthenticationFacadeImpl authenticationFacade;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository, CategoryMapper categoryMapper, AuthenticationFacadeImpl authenticationFacade) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public CategoryDTO addCategory(AddCategoryDTO addCategoryDTO) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);
        Category category = new Category(StringUtils.capitalize(addCategoryDTO.categoryName().toLowerCase()), addCategoryDTO.categoryAlias().toLowerCase(), user);
        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategories() {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);
        return categoryRepository.findByCreateUser(user).stream()
            .map(categoryMapper::toCategoryDTO)
            .toList();
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);
        Category category = categoryRepository.findByIdAndCreateUser(categoryId, user)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId + " for the authenticated user"));
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, UpdateCategoryDTO updateCategoryDTO) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);
        Category category = categoryRepository.findByIdAndCreateUser(categoryId, user)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId + " for the authenticated user"));

        category.setCategoryName(StringUtils.capitalize(updateCategoryDTO.categoryName().toLowerCase()));
        category.setCategoryAlias(updateCategoryDTO.categoryAlias().toLowerCase());

        return categoryMapper.toCategoryDTO(categoryRepository.save(category));
    }
}