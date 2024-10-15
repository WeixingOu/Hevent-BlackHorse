package com.itbh.hevent.dtos;

import com.itbh.hevent.enums.ArticleState;

import java.time.LocalDateTime;

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String coverImg;
    private ArticleState state;
    private Long categoryId;
    private Long createUserId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public ArticleDTO(Long id, String title, String content, String coverImg, ArticleState state, Long categoryId, Long createUserId, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
        this.state = state;
        this.categoryId = categoryId;
        this.createUserId = createUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public ArticleState getState() {
        return state;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
