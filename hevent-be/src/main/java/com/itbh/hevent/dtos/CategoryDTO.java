package com.itbh.hevent.dtos;

import java.time.LocalDateTime;

public class CategoryDTO {
    private Long id;
    private String categoryName;
    private String categoryAlias;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CategoryDTO(Long id, String categoryName, String categoryAlias, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryAlias = categoryAlias;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
