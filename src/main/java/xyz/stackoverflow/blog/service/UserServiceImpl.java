package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.dao.*;
import xyz.stackoverflow.blog.pojo.po.*;
import xyz.stackoverflow.blog.util.PasswordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现
 *
 * @author 凉衫薄
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserPO> selectByPage(Page page) {
        return userDao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserPO> selectByCondition(Map<String, Object> searchMap) {
        return userDao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPO selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPO insert(UserPO user) {
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        userDao.insert(user);
        return userDao.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<UserPO> list) {
        for (UserPO user : list) {
            user.setSalt(PasswordUtil.getSalt());
            user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        }
        return userDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPO deleteById(String id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return userDao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPO update(UserPO user) {
        userDao.update(user);
        return userDao.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<UserPO> list) {
        return userDao.batchUpdate(list);
    }

    /**
     * 授予用户角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRolePO grantRole(String userId, String roleId) {

        UserPO user = userDao.selectById(userId);
        RolePO role = roleDao.selectById(roleId);

        if (user == null || role == null) {
            return null;
        }

        UserRolePO userRole = new UserRolePO();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRoleDao.insert(userRole);
        return userRole;
    }

    /**
     * 收回用户角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRolePO revokeRole(String userId, String roleId) {

        List<UserRolePO> userRoleList = userRoleDao.selectByCondition(new HashMap<String, Object>() {{
            put("userId", userId);
            put("roleId", roleId);
        }});

        if (userRoleList.size() == 0) {
            return null;
        }

        userRoleDao.deleteById(userRoleList.get(0).getId());
        return userRoleList.get(0);

    }

    /**
     * 返回所有角色
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RolePO> getRoleByUserId(String userId) {
        List<UserRolePO> userRoles = userRoleDao.selectByCondition(new HashMap<String, Object>() {{
            put("userId", userId);
        }});
        List<RolePO> roles = null;
        if ((null != userRoles) && (userRoles.size() != 0)) {
            roles = new ArrayList<>();
            for (UserRolePO userRole : userRoles) {
                RolePO role = roleDao.selectById(userRole.getId());
                roles.add(role);
            }
        }
        return roles;
    }

    /**
     * 返回所有权限
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PermissionPO> getPermissionByUserId(String userId) {
        List<RolePO> roles = getRoleByUserId(userId);
        Map<String, PermissionPO> permissionMap = new HashMap<>();
        for (RolePO role : roles) {
            List<RolePermissionPO> rolePermissions = rolePermissionDao.selectByCondition(new HashMap<String, Object>() {{
                put("roleId", role.getId());
            }});
            if ((null != rolePermissions) && (rolePermissions.size() != 0)) {
                for (RolePermissionPO rolePermission : rolePermissions) {
                    PermissionPO permission = permissionDao.selectById(rolePermission.getPermissionId());
                    permissionMap.put(permission.getId(), permission);
                }
            }
        }
        List<PermissionPO> permissions = (List<PermissionPO>) permissionMap.values();
        return permissions;
    }

}