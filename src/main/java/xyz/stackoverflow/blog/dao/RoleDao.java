package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 角色表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface RoleDao {

    List<Role> selectByPage(Page page);

    List<Role> selectByCondition(Map<String, Object> searchMap);

    Role selectById(String id);

    int insert(Role role);

    int batchInsert(List<Role> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Role role);

    int batchUpdate(List<Role> list);

}
