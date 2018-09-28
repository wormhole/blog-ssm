package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<String> getRoleByUserId(String userId);
    Role insertUserRole(Role role);
}
