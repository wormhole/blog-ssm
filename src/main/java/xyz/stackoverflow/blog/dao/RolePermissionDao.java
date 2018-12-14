package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 角色-权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface RolePermissionDao {

    List<RolePermission> selectByPage(Page page);

    List<RolePermission> selectByCondition(Map<String,String> searchMap);

    RolePermission selectById(String id);

    int insert(RolePermission rolePermission);

    int batchInsert(List<RolePermission> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(String id);

    int batchUpdate(List<RolePermission> list);


    List<RolePermission> getRolePermissionByRoleId(String roleId);

    RolePermission insertRolePermission(RolePermission rolePermission);
}
