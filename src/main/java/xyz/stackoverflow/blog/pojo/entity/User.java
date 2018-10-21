package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author 凉衫薄
 */
public class User implements Serializable {

    protected String id;
    protected String email;
    protected String password;
    protected String nickname;
    protected String salt;
    protected String headUrl;
    protected String signature;
    protected Integer deleteAble;

    public User(){

    }

    public User(String id, String email, String password, String nickname, String salt, String headUrl, String signature, Integer deleteAble) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.salt = salt;
        this.headUrl = headUrl;
        this.signature = signature;
        this.deleteAble = deleteAble;
    }

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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
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