package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String salt;
    private String headurl;
    private String signature;
    private Integer deleteAble;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }
}