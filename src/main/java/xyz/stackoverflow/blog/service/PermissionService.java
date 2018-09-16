package xyz.stackoverflow.blog.service;

import java.util.Set;

public interface PermissionService {
    Set<String> getPermissionByRole(String role);
    Set<String> getAllPermission(String[] roles);
}
