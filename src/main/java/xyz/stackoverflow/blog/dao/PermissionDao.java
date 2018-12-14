package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface PermissionDao {

    List<Permission> selectByPage(Page page);

    List<Permission> selectByCondition(Map<String,String> searchMap);

    Permission selectById(String id);

    int insert(Permission permission);

    int batchInsert(List<Permission> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Permission permission);

    int batchUpdate(List<Permission> list);


    int insertPermission(Permission permission);

    Permission getPermissionById(String id);

    Permission getPermissionByCode(String permissionCode);

    List<Permission> getAllPermission();
}
