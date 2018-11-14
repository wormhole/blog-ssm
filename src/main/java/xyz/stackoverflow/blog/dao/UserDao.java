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

    int insertUser(User user);

    int updateUser(User user);

    int isExistEmail(String email);

}