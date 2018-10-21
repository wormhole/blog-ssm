package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.entity.UserRole;

import java.util.Set;

/**
 * 用户服务接口
 *
 * @author 凉衫薄
 */
public interface UserService {

    User getUserById(String userId);

    User getUserByEmail(String email);

    User getAdmin();

    User insertUser(User user);

    User updateHeadUrl(User user);

    User updatePassword(User user);

    User updateBaseInfo(User user);

    boolean isExist(String email);

    int getUserCount();

    UserRole grantRole(String roleCode,String userId);

    Set<String> getRoleCodeByUserId(String userId);

    Set<String> getPermissionCodeByUserId(String userId);

    User deleteUserById(String userId);
}