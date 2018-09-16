package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.List;

@Repository
public interface PermissionDao {
    List<Permission> getPermissionByRole(String role);
}
