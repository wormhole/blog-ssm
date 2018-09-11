package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;

public interface UserService {
    User getUserByEmail(String email);
    User addUser(User user);
    User updateHeadurl(User user);
    User updatePassword(User user);
    User udpateNickname(User user);
    User udpateEmail(User user);
    User updateEmailAndNickname(User user);
    boolean isExist(String email);
}