package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.List;

/**
 * 权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface PermissionDao {

    int insertPermission(Permission permission);

    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();
}
