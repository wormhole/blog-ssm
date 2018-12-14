package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 用户表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserDao {

    List<User> selectByPage(PageParameter pageParameter);

    List<User> selectByCondition(Map<String,String> searchMap);

    User selectById(String id);

    int insert(User user);

    int batchInsert(List<User> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(User user);

    int batchUpdate(List<User> list);


    User getUserById(String userId);

    User getUserByEmail(String email);

    User getAdmin();

    int insertUser(User user);

    int updateUser(User user);

    int isExistEmail(String email);

}