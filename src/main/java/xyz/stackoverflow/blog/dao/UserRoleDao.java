package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.UserRole;

import java.util.List;

/**
 * 用户-角色表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface UserRoleDao {
    List<UserRole> getUserRoleByUserId(String userId);

    int insertUserRole(UserRole userRole);
}
