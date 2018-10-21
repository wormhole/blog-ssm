package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;

import java.util.List;

/**
 * 角色-权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface RolePermissionDao {
    List<RolePermission> getRolePermissionByRoleId(String roleId);
    RolePermission insertRolePermission(RolePermission rolePermission);
}
