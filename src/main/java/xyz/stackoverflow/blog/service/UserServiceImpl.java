package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.*;
import xyz.stackoverflow.blog.pojo.entity.*;
import xyz.stackoverflow.blog.util.IdGenerator;
import xyz.stackoverflow.blog.util.PasswordUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'user:'+#email", unless = "#result == null")
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email", condition = "#result != null")
    public User getAdmin() {
        return userDao.getAdmin();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email", condition = "#result != null")
    public User insertUser(User user) {
        user.setHeadUrl("/static/custom/image/default.jpeg");
        user.setSignature("这个家伙很懒，没有留下任何东西。");
        user.setId(IdGenerator.getId());
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        userDao.insertUser(user);
        return userDao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email", condition = "#result != null")
    public User updateHeadUrl(User user) {
        userDao.updateHeadUrl(user);
        return userDao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email", condition = "#result != null")
    public User updatePassword(User user) {
        user.setSalt(PasswordUtil.getSalt());
        user.setPassword(PasswordUtil.encryptPassword(user.getSalt(), user.getPassword()));
        userDao.updatePassword(user);
        return userDao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'user:'+#result.email", condition = "#result != null")
    public User updateBaseInfo(User user) {
        userDao.updateBaseInfo(user);
        return userDao.getUserByEmail(user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExist(String email) {
        if (userDao.isExist(email) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRole grantRole(String roleCode, String userId) {
        Role role = roleDao.getRoleByCode(roleCode);
        UserRole userRole = new UserRole();
        userRole.setId(IdGenerator.getId());
        userRole.setRoleId(role.getId());
        userRole.setUserId(userId);
        userRoleDao.insertUserRole(userRole);
        return userRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<String> getRoleCodeByUserId(String userId) {
        List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(userId);
        Set<String> retSet = null;
        if ((null != userRoleList) && (userRoleList.size() != 0)) {
            retSet = new HashSet();
            for (UserRole userRole : userRoleList) {
                Role role = roleDao.getRoleById(userRole.getRoleId());
                retSet.add(role.getRoleCode());
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
            Role role = roleDao.getRoleByCode(roleCode);
            List<RolePermission> rolePermissionList = rolePermissionDao.getRolePermissionByRoleId(role.getId());
            if ((null != rolePermissionList) && (rolePermissionList.size() != 0)) {
                retSet = new HashSet();
                for (RolePermission rolePermission : rolePermissionList) {
                    Permission permission = permissionDao.getPermissionById(rolePermission.getPermissionId());
                    retSet.add(permission.getPermissionCode());
                }
            }
        }
        return retSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'user:'+#result.email", condition = "#result!=null", beforeInvocation = false)
    public User deleteUserById(String userId) {
        User user = userDao.getUserById(userId);
        if (userDao.deleteUserById(userId) == 1) {
            return user;
        } else {
            return null;
        }
    }
}