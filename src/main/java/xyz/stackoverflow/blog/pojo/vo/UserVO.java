package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.User;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 用户VO
 */
public class UserVO extends User {

    protected String oldPassword;
    protected String vcode;

    public UserVO(){

    }

    public UserVO(String id, String email, String password, String nickname, String salt, String headUrl, String signature, Integer deleteAble, String oldPassword, String vcode) {
        super(id, email, password, nickname, salt, headUrl, signature, deleteAble);
        this.oldPassword = oldPassword;
        this.vcode = vcode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setSignature(signature);
        user.setPassword(password);
        user.setHeadUrl(headUrl);
        user.setSalt(salt);
        user.setDeleteAble(deleteAble);
        return user;
    }
}
