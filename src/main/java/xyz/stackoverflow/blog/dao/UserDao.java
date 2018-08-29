package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.User;

@Repository
public interface UserDao {
    User getUserByEmail(String email);
    int addUser(User user);
    int updateHeadurl(User user);
    int updatePassword(User user);
    int updateNickname(User name);
}