package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.dao.RolePermissionDao;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;
import xyz.stackoverflow.blog.pojo.po.RolePO;
import xyz.stackoverflow.blog.pojo.po.RolePermissionPO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色服务实现
 *
 * @author 凉衫薄
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RolePO> selectByPage(Page page) {
        return roleDao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RolePO> selectByCondition(Map<String, Object> searchMap) {
        return roleDao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePO selectById(String id) {
        return roleDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePO insert(RolePO role) {
        roleDao.insert(role);
        return roleDao.selectById(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<RolePO> list) {
        return roleDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePO deleteById(String id) {
        RolePO role = roleDao.selectById(id);
        roleDao.deleteById(id);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return roleDao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePO update(RolePO role) {
        roleDao.update(role);
        return roleDao.selectById(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<RolePO> list) {
        return roleDao.batchUpdate(list);
    }

    /**
     * 给角色赋予权限
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePermissionPO grantPermission(String roleId, String permissionId) {
        RolePO role = roleDao.selectById(roleId);
        PermissionPO permission = permissionDao.selectById(permissionId);

        if (role == null || permission == null) {
            return null;
        }

        RolePermissionPO rolePermission = new RolePermissionPO();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermissionDao.insert(rolePermission);

        return rolePermission;
    }

    /**
     * 给角色收回权限
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePermissionPO revokePermission(String roleId, String permissionId) {

        List<RolePermissionPO> rolePermissionList = rolePermissionDao.selectByCondition(new HashMap<String, Object>() {{
            put("roleId", roleId);
            put("permissionId", permissionId);
        }});

        if (rolePermissionList.size() == 0) {
            return null;
        }

        rolePermissionDao.deleteById(rolePermissionList.get(0).getId());
        return rolePermissionList.get(0);
    }

    /**
     * 获取所有权限
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PermissionPO> getPermissionByRoleId(String roleId) {
        List<PermissionPO> retList = null;

        List<RolePermissionPO> rolePermissionList = rolePermissionDao.selectByCondition(new HashMap<String, Object>() {{
            put("roleId", roleId);
        }});
        if ((null != rolePermissionList) && (rolePermissionList.size() != 0)) {
            retList = new ArrayList<>();
            for (RolePermissionPO rolePermission : rolePermissionList) {
                PermissionPO permission = permissionDao.selectById(rolePermission.getPermissionId());
                retList.add(permission);
            }
        }

        return retList;
    }

}
