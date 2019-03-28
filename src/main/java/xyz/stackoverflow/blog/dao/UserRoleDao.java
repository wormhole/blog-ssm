package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.UserRolePO;

import java.util.List;
import java.util.Map;

/**
 * 用户-角色表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserRoleDao {

    List<UserRolePO> selectByPage(Page page);

    List<UserRolePO> selectByCondition(Map<String, Object> searchMap);

    UserRolePO selectById(String id);

    int insert(UserRolePO userRole);

    int batchInsert(List<UserRolePO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(UserRolePO userRole);

    int batchUpdate(List<UserRolePO> list);

}
