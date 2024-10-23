package com.itbh.hevent.Repositories;

import com.itbh.hevent.enums.ArticleState;
import com.itbh.hevent.models.Article;
import com.itbh.hevent.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByCreateUserAndCategoryIdAndState(User createUser, Long categoryId, ArticleState state, Pageable pageable);

    Page<Article> findByCreateUserAndState(User createUser, ArticleState state, Pageable pageable);

    Page<Article> findByCreateUserAndCategoryId(User createUser, Long categoryId, Pageable pageable);

    Page<Article> findByCreateUser(User createUser, Pageable pageable);

    Optional<Article> findByIdAndCreateUser(Long articleId, User user);
}
