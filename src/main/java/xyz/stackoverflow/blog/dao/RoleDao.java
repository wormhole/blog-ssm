package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.RolePO;

import java.util.List;
import java.util.Map;

/**
 * 角色表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface RoleDao {

    List<RolePO> selectByPage(Page page);

    List<RolePO> selectByCondition(Map<String, Object> searchMap);

    RolePO selectById(String id);

    int insert(RolePO role);

    int batchInsert(List<RolePO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(RolePO role);

    int batchUpdate(List<RolePO> list);

}
