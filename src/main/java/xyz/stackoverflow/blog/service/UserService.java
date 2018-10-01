package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;

public interface UserService {
    User getUserByEmail(String email);

    User insertUser(User user);

    User updateHeadUrl(User user);

    User updatePassword(User user);

    User updateBaseInfo(User user);

    boolean isExist(String email);
}