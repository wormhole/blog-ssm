package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.UserRole;

import java.util.List;

@Repository
public interface UserRoleDao {
    List<UserRole> getUserRoleByUserId(String userId);

    int insertUserRole(UserRole userRole);
}
