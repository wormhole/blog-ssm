package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.dao.RolePermissionDao;
import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.pojo.entity.RolePermission;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

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
    public List<Role> selectByPage(PageParameter pageParameter) {
        return roleDao.selectByPage(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> selectByCondition(Map<String, String> searchMap) {
        return roleDao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role selectById(String id) {
        return roleDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insert(Role role) {
        role.setId(UUIDGenerator.getId());
        roleDao.insert(role);
        return roleDao.selectById(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Role> list) {
        return roleDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role deleteById(String id) {
        Role role = roleDao.getRoleById(id);
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
    public Role update(Role role) {
        roleDao.update(role);
        return roleDao.selectById(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Role> list) {
        return roleDao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getRoleByCode(String roleCode) {
        return roleDao.getRoleByCode(roleCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insertRole(Role role) {
        role.setId(UUIDGenerator.getId());
        roleDao.insertRole(role);
        return roleDao.getRoleById(role.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RolePermission grantPermission(String roleId, String permissionCode) {
        Permission permission = permissionDao.getPermissionByCode(permissionCode);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(UUIDGenerator.getId());
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permission.getId());
        rolePermissionDao.insertRolePermission(rolePermission);
        return rolePermission;
    }
}
