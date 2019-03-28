package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.entity.UserRole;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 *
 * @author 凉衫薄
 */
public interface UserService {

    List<User> selectByPage(Page page);

    List<User> selectByCondition(Map<String, Object> searchMap);

    User selectById(String id);

    User insert(User user);

    int batchInsert(List<User> list);

    User deleteById(String id);

    int batchDeleteById(List<String> list);

    User update(User user);

    int batchUpdate(List<User> list);

    UserRole grantRole(String userId, String roleCode);

    UserRole revokeRole(String userId, String roleCode);

    List<Role> getRoleByUserId(String userId);

    List<Permission> getPermissionByUserId(String userId);
}