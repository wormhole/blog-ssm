package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;

public interface UserService {
    User getUserByEmail(String email);
    int addUser(User user);
    int updateHeadurl(User user);
    int updatePassword(User user);
    int udpateNickname(User user);
}