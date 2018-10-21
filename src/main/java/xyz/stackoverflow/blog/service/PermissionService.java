package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author 凉衫薄
 */
public interface PermissionService {

    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();

    Permission insertPermission(Permission permission);
}
