package com.itbh.hevent.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String userPic;
    @JsonFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime updateTime;

    public UserDTO(Long id, String username, String nickname, String email, String userPic, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.userPic = userPic;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getUserPic() {
        return userPic;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
