package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();

    Permission insertPermission(Permission permission);
}
