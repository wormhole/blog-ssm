package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.User;

/**
 * 用户表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserDao {

    User getUserById(String userId);

    User getUserByEmail(String email);

    User getAdmin();

    int getUserCount();

    int insertUser(User user);

    int updatePassword(User user);

    int updateBaseInfo(User user);

    int isExist(String email);

    int deleteUserById(String userId);
}