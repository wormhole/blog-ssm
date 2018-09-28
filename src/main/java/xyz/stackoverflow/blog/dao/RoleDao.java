package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Role;

import java.util.List;

@Repository
public interface RoleDao {
    List<Role> getRoleByUserId(String userId);
    int insertUserRole(Role role);
}
