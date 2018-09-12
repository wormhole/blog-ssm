package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.User;

@Repository
public interface UserDao {
    User getUserByEmail(String email);
    int addUser(User user);
    int updateHeadUrl(User user);
    int updatePassword(User user);
    int updateNickname(User user);
    int updateEmail(User user);
    int updateEmailAndNickname(User user);
    int isExist(String email);
}