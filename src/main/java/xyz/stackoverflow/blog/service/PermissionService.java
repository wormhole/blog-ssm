package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 权限服务接口
 *
 * @author 凉衫薄
 */
public interface PermissionService {

    List<Permission> selectByPage(Page page);

    List<Permission> selectByCondition(Map<String,String> searchMap);

    Permission selectById(String id);

    Permission insert(Permission permission);

    int batchInsert(List<Permission> list);

    Permission deleteById(String id);

    int batchDeleteById(List<String> list);

    Permission update(Permission permission);

    int batchUpdate(List<Permission> list);


    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();

    Permission insertPermission(Permission permission);
}
