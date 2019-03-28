package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.RolePermissionPO;

import java.util.List;
import java.util.Map;

/**
 * 角色-权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface RolePermissionDao {

    List<RolePermissionPO> selectByPage(Page page);

    List<RolePermissionPO> selectByCondition(Map<String, Object> searchMap);

    RolePermissionPO selectById(String id);

    int insert(RolePermissionPO rolePermission);

    int batchInsert(List<RolePermissionPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(String id);

    int batchUpdate(List<RolePermissionPO> list);


    List<RolePermissionPO> getRolePermissionByRoleId(String roleId);

    RolePermissionPO insertRolePermission(RolePermissionPO rolePermission);
}
