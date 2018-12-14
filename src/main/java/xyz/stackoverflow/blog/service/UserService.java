package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.entity.UserRole;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务接口
 *
 * @author 凉衫薄
 */
public interface UserService {

    List<User> selectByPage(PageParameter pageParameter);

    List<User> selectByCondition(Map<String,String> searchMap);

    User selectById(String id);

    User insert(User user);

    int batchInsert(List<User> list);

    User deleteById(String id);

    int batchDeleteById(List<String> list);

    User update(User user);

    int batchUpdate(List<User> list);


    User getUserById(String userId);

    User getUserByEmail(String email);

    User getAdmin();

    User insertUser(User user);

    User updateUser(User user);

    boolean isExistEmail(String email);

    UserRole grantRole(String roleCode,String userId);

    Set<String> getRoleCodeByUserId(String userId);

    Set<String> getPermissionCodeByUserId(String userId);
}