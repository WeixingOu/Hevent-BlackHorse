package com.itbh.hevent.controllers;

import com.itbh.hevent.dtos.ArticleDTO;
import com.itbh.hevent.dtos.PageBean;
import com.itbh.hevent.dtos.records.AddArticleDTO;
import com.itbh.hevent.dtos.records.UpdateArticleDTO;
import com.itbh.hevent.enums.ArticleState;
import com.itbh.hevent.exceptions.api.ApiResponse;
import com.itbh.hevent.services.ArticleService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity<ApiResponse<ArticleDTO>> addArticle(@Valid @RequestBody AddArticleDTO addArticleDTO) {
        return new ResponseEntity<>(ApiResponse.success(articleService.addArticle(addArticleDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<ApiResponse<ArticleDTO>> updateArticle(@PathVariable Long articleId, @Valid @RequestBody UpdateArticleDTO updateArticleDTO) {
        return new ResponseEntity<>(ApiResponse.success(articleService.updateArticle(articleId, updateArticleDTO)), HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<ApiResponse<PageBean<ArticleDTO>>> getArticles(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam(required = false) Long categoryId,
        @RequestParam(required = false) ArticleState state
    ) {
        return new ResponseEntity<>(ApiResponse.success(articleService.getArticles(page, size, categoryId, state)), HttpStatus.OK);
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(ApiResponse.success(null), HttpStatus.NO_CONTENT);
    }
}
