package xyz.stackoverflow.blog.service;

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

    List<Role> selectByCondition(Map<String,String> searchMap);

    Role selectById(String id);

    Role insert(Role role);

    int batchInsert(List<Role> list);

    Role deleteById(String id);

    int batchDeleteById(List<String> list);

    Role update(Role role);

    int batchUpdate(List<Role> list);


    Role getRoleById(String id);

    Role getRoleByCode(String roleCode);

    List<Role> getAllRole();

    Role insertRole(Role role);

    RolePermission grantPermission(String roleId,String permissionCode);
}
