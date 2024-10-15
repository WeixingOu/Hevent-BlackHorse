package com.itbh.hevent.services;

import com.itbh.hevent.dtos.ArticleDTO;
import com.itbh.hevent.dtos.PageBean;
import com.itbh.hevent.dtos.records.AddArticleDTO;
import com.itbh.hevent.enums.ArticleState;

public interface ArticleService {
    ArticleDTO addArticle(AddArticleDTO addArticleDTO);

    PageBean<ArticleDTO> getArticles(int page, int size, Long categoryId, ArticleState state);
}
