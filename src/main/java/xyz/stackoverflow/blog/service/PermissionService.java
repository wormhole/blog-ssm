package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 权限服务接口
 */
public interface PermissionService {

    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();

    Permission insertPermission(Permission permission);
}
