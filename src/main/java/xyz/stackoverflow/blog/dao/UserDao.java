package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserDao {

    List<User> selectByPage(Page page);

    List<User> selectByCondition(Map<String, Object> searchMap);

    User selectById(String id);

    int insert(User user);

    int batchInsert(List<User> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(User user);

    int batchUpdate(List<User> list);

}