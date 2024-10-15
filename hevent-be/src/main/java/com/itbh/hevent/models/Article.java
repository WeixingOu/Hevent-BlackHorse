package com.itbh.hevent.models;

import com.itbh.hevent.enums.ArticleState;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content;
    @Column(name = "cover_img", nullable = false, length = 128)
    private String coverImg;
    @Enumerated(EnumType.STRING)
    private ArticleState state;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User createUser;
    @Column(nullable = false)
    private LocalDateTime createTime;
    @Column(nullable = false)
    private LocalDateTime updateTime;

    public Article() {
    }

    public Article(String title, String content, String coverImg, ArticleState state, Category category, User createUser) {
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
        this.state = state;
        this.category = category;
        this.createUser = createUser;
    }

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public ArticleState getState() {
        return state;
    }

    public void setState(ArticleState state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

}
