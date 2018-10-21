package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Role;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 角色服务接口
 */
public interface RoleService {

    Role getRoleById(String id);

    Role getRoleByCode(String roleCode);

    List<Role> getAllRole();

    Role insertRole(Role role);
}
