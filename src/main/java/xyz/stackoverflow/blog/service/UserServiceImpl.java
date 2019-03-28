package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.*;
import xyz.stackoverflow.blog.pojo.entity.*;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.util.db.Page;

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
    public List<User> selectByPage(Page page) {
        return userDao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> selectByCondition(Map<String, Object> searchMap) {
        return userDao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        userDao.insert(user);
        return userDao.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<User> list) {
        for (User user : list) {
            user.setSalt(PasswordUtil.getSalt());
            user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        }
        return userDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User deleteById(String id) {
        return userDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return userDao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        userDao.update(user);
        return userDao.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<User> list) {
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
    public UserRole grantRole(String userId, String roleId) {

        User user = userDao.selectById(userId);
        Role role = roleDao.selectById(roleId);

        if (user == null || role == null) {
            return null;
        }

        UserRole userRole = new UserRole();
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
    public UserRole revokeRole(String userId, String roleId) {

        List<UserRole> userRoleList = userRoleDao.selectByCondition(new HashMap<String, Object>() {{
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
    public List<Role> getRoleByUserId(String userId) {
        List<UserRole> userRoles = userRoleDao.selectByCondition(new HashMap<String, Object>() {{
            put("userId", userId);
        }});
        List<Role> roles = null;
        if ((null != userRoles) && (userRoles.size() != 0)) {
            roles = new ArrayList<>();
            for (UserRole userRole : userRoles) {
                Role role = roleDao.selectById(userRole.getId());
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
    public List<Permission> getPermissionByUserId(String userId) {
        List<Role> roles = getRoleByUserId(userId);
        Map<String, Permission> permissionMap = new HashMap<>();
        for (Role role : roles) {
            List<RolePermission> rolePermissions = rolePermissionDao.selectByCondition(new HashMap<String, Object>() {{
                put("roleId", role.getId());
            }});
            if ((null != rolePermissions) && (rolePermissions.size() != 0)) {
                for (RolePermission rolePermission : rolePermissions) {
                    Permission permission = permissionDao.selectById(rolePermission.getPermissionId());
                    permissionMap.put(permission.getId(), permission);
                }
            }
        }
        List<Permission> permissions = (List<Permission>) permissionMap.values();
        return permissions;
    }

}