package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;
import xyz.stackoverflow.blog.pojo.po.RolePO;
import xyz.stackoverflow.blog.pojo.po.RolePermissionPO;

import java.util.List;
import java.util.Map;

/**
 * 角色服务接口
 *
 * @author 凉衫薄
 */
public interface RoleService {

    List<RolePO> selectByPage(Page page);

    List<RolePO> selectByCondition(Map<String, Object> searchMap);

    RolePO selectById(String id);

    RolePO insert(RolePO role);

    int batchInsert(List<RolePO> list);

    RolePO deleteById(String id);

    int batchDeleteById(List<String> list);

    RolePO update(RolePO role);

    int batchUpdate(List<RolePO> list);

    RolePermissionPO grantPermission(String roleId, String permissionId);

    RolePermissionPO revokePermission(String roleId, String permissionId);

    List<PermissionPO> getPermissionByRoleId(String roleId);

}
