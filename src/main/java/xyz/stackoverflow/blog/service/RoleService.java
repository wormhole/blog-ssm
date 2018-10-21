package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author 凉衫薄
 */
public interface RoleService {

    Role getRoleById(String id);

    Role getRoleByCode(String roleCode);

    List<Role> getAllRole();

    Role insertRole(Role role);

    RolePermission grantPermission(String roleId,String permissionCode);
}
