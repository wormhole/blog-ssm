package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 角色-权限表DAO
 */
@Repository
public interface RolePermissionDao {
    List<RolePermission> getRolePermissionByRoleId(String roleId);
    RolePermission insertRolePermission(RolePermission rolePermission);
}
