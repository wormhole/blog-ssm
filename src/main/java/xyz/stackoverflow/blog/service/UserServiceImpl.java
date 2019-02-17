package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.*;
import xyz.stackoverflow.blog.pojo.entity.*;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.*;

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
        user.setId(UUIDGenerator.getId());
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        userDao.insert(user);
        return userDao.selectById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<User> list) {
        for (User user : list) {
            user.setId(UUIDGenerator.getId());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRole grantRole(String roleCode, String userId) {
        Role role = roleDao.selectByCondition(new HashMap<String, Object>() {{
            put("code", roleCode);
        }}).get(0);
        UserRole userRole = new UserRole();
        userRole.setId(UUIDGenerator.getId());
        userRole.setRoleId(role.getId());
        userRole.setUserId(userId);
        userRoleDao.insert(userRole);
        return userRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<String> getRoleCodeByUserId(String userId) {
        List<UserRole> userRoleList = userRoleDao.selectByCondition(new HashMap<String, Object>() {{
            put("userId", userId);
        }});
        Set<String> retSet = null;
        if ((null != userRoleList) && (userRoleList.size() != 0)) {
            retSet = new HashSet<>();
            for (UserRole userRole : userRoleList) {
                Role role = roleDao.selectById(userRole.getRoleId());
                retSet.add(role.getCode());
            }
        }
        return retSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<String> getPermissionCodeByUserId(String userId) {
        Set<String> roleCodeSet = getRoleCodeByUserId(userId);
        Set<String> retSet = null;
        for (String roleCode : roleCodeSet) {
            Role role = roleDao.selectByCondition(new HashMap<String, Object>() {{
                put("code", roleCode);
            }}).get(0);
            List<RolePermission> rolePermissionList = rolePermissionDao.getRolePermissionByRoleId(role.getId());
            if ((null != rolePermissionList) && (rolePermissionList.size() != 0)) {
                retSet = new HashSet<>();
                for (RolePermission rolePermission : rolePermissionList) {
                    Permission permission = permissionDao.selectById(rolePermission.getPermissionId());
                    retSet.add(permission.getCode());
                }
            }
        }
        return retSet;
    }

}