package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 角色服务接口
 *
 * @author 凉衫薄
 */
public interface RoleService {

    List<Role> selectByPage(Page page);

    List<Role> selectByCondition(Map<String, Object> searchMap);

    Role selectById(String id);

    Role insert(Role role);

    int batchInsert(List<Role> list);

    Role deleteById(String id);

    int batchDeleteById(List<String> list);

    Role update(Role role);

    int batchUpdate(List<Role> list);

    RolePermission grantPermission(String roleId, String permissionId);

    RolePermission revokePermission(String roleId, String permissionId);

    List<Permission> getPermissionByRoleId(String roleId);

}
