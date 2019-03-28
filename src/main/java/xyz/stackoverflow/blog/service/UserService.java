package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;
import xyz.stackoverflow.blog.pojo.po.RolePO;
import xyz.stackoverflow.blog.pojo.po.UserPO;
import xyz.stackoverflow.blog.pojo.po.UserRolePO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 *
 * @author 凉衫薄
 */
public interface UserService {

    List<UserPO> selectByPage(Page page);

    List<UserPO> selectByCondition(Map<String, Object> searchMap);

    UserPO selectById(String id);

    UserPO insert(UserPO user);

    int batchInsert(List<UserPO> list);

    UserPO deleteById(String id);

    int batchDeleteById(List<String> list);

    UserPO update(UserPO user);

    int batchUpdate(List<UserPO> list);

    UserRolePO grantRole(String userId, String roleCode);

    UserRolePO revokeRole(String userId, String roleCode);

    List<RolePO> getRoleByUserId(String userId);

    List<PermissionPO> getPermissionByUserId(String userId);
}