package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(String id);

    Role getRoleByCode(String roleCode);

    List<Role> getAllRole();

    Role insertRole(Role role);
}
