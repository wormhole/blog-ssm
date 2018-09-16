package xyz.stackoverflow.blog.service;

import java.util.Set;

public interface RoleService {
    Set<String> getRoleByUserId(String userId);
}
