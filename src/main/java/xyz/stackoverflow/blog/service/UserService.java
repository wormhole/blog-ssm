package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.entity.UserRole;

import java.util.Set;

public interface UserService {
    User getUserByEmail(String email);

    User insertUser(User user);

    User updateHeadUrl(User user);

    User updatePassword(User user);

    User updateBaseInfo(User user);

    boolean isExist(String email);

    UserRole grantRole(String roleCode,String userId);

    Set<String> getRoleCodeByUserId(String userId);

    Set<String> getPermissionCodeByUserId(String userId);
}