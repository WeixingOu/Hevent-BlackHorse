package com.itbh.hevent.Repositories;

import com.itbh.hevent.dtos.CategoryDTO;
import com.itbh.hevent.models.Category;
import com.itbh.hevent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCreateUser(User user);
    Optional<Category> findByIdAndCreateUser(Long id, User user);
}
