package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;

import java.util.List;

@Repository
public interface RolePermissionDao {
    List<RolePermission> getRolePermissionByRoleId(String roleId);
}
