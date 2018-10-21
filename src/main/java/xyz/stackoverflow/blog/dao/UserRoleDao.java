package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.UserRole;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 用户-角色表DAO
 */
@Repository
public interface UserRoleDao {
    List<UserRole> getUserRoleByUserId(String userId);

    int insertUserRole(UserRole userRole);
}
