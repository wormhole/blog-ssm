package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.User;

import java.io.Serializable;

public class BaseInfoVO implements Serializable {
    private String email;
    private String nickname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User toUser(){
        User user = new User();
        user.setNickname(nickname);
        user.setEmail(email);
        return user;
    }
}
