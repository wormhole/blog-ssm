package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.UserPO;

import java.util.List;
import java.util.Map;

/**
 * 用户表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserDao {

    List<UserPO> selectByPage(Page page);

    List<UserPO> selectByCondition(Map<String, Object> searchMap);

    UserPO selectById(String id);

    int insert(UserPO user);

    int batchInsert(List<UserPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(UserPO user);

    int batchUpdate(List<UserPO> list);

}