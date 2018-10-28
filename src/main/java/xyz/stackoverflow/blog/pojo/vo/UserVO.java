package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.User;

/**
 * 用户VO
 *
 * @author 凉衫薄
 */
public class UserVO extends User {

    protected String oldPassword;
    protected String vcode;

    public UserVO(){

    }

    public UserVO(String id, String email, String password, String nickname, String salt, Integer deleteAble, String oldPassword, String vcode) {
        super(id, email, password, nickname, salt, deleteAble);
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

    /**
     * 转换成实体类
     *
     * @return 转换后的实体类
     */
    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setSalt(salt);
        user.setDeleteAble(deleteAble);
        return user;
    }
}
