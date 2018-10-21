package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Role;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 角色表DAO
 */
@Repository
public interface RoleDao {

    int insertRole(Role role);

    Role getRoleById(String id);

    Role getRoleByCode(String roleCode);

    List<Role> getAllRole();
}
