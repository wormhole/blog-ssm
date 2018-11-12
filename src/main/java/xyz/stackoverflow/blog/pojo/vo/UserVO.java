package xyz.stackoverflow.blog.pojo.vo;

import org.hibernate.validator.constraints.Length;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.util.web.SuperVO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户VO
 *
 * @author 凉衫薄
 */
public class UserVO implements SuperVO {

    private String id;

    @NotNull(message = "邮箱不能为空", groups = {RegisterGroup.class, UpdateBaseGroup.class})
    @Length(min = 1, max = 30, message = "邮箱长度只能在1到30之间", groups = {RegisterGroup.class, UpdateBaseGroup.class})
    @Email(message = "邮箱格式错误", groups = {RegisterGroup.class, UpdateBaseGroup.class})
    private String email;

    @NotNull(message = "密码不能为空", groups = {RegisterGroup.class, UpdatePasswordGroup.class})
    @Length(min = 6, max = 20, message = "密码长度只能在6到20之间", groups = {RegisterGroup.class, UpdatePasswordGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "密码只能为数字字母下划线", groups = {RegisterGroup.class, UpdatePasswordGroup.class})
    private String password;

    @NotNull(message = "昵称不能为空", groups = {RegisterGroup.class, UpdateBaseGroup.class})
    @Length(min = 1, max = 20, message = "昵称长度只能在1到20之间", groups = {RegisterGroup.class, UpdateBaseGroup.class})
    private String nickname;

    private String salt;
    private Integer deleteAble;

    //以下为扩展字段
    @NotNull(message = "旧密码不能为空", groups = {UpdatePasswordGroup.class})
    private String oldPassword;

    @NotNull(message = "验证码不能为空", groups = {RegisterGroup.class})
    private String vcode;

    public interface RegisterGroup {
    }

    public interface UpdateBaseGroup {
    }

    public interface UpdatePasswordGroup {
    }

    public UserVO() {

    }

    public UserVO(String id, String email, String password, String nickname, String salt, Integer deleteAble, String oldPassword, String vcode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.salt = salt;
        this.deleteAble = deleteAble;
        this.oldPassword = oldPassword;
        this.vcode = vcode;
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

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
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
    public User toUser() {
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
