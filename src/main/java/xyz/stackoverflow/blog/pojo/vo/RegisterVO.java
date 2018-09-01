package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.User;

import java.io.Serializable;

public class RegisterVO implements Serializable {

    private String email;
    private String nickname;
    private String password;
    private String vcode;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public User toUser(){
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        return user;
    }

}
