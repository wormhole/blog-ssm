package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.dao.RolePermissionDao;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.util.db.Page;
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
    public List<Role> selectByPage(Page page) {
        return roleDao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> selectByCondition(Map<String, Object> searchMap) {
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
        Role role = roleDao.selectById(id);
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

}
