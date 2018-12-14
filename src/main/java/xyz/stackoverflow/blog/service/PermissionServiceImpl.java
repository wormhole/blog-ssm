package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 权限服务实现
 *
 * @author 凉衫薄
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> selectByPage(Page page) {
        return dao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission insert(Permission permission) {
        permission.setId(UUIDGenerator.getId());
        dao.insert(permission);
        return dao.selectById(permission.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Permission> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission deleteById(String id) {
        Permission permission = dao.selectById(id);
        dao.deleteById(id);
        return permission;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission update(Permission permission) {
        dao.update(permission);
        return dao.selectById(permission.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Permission> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission getPermissionById(String id) {
        return dao.getPermissionById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission getPermissionByCode(String permissionCode) {
        return dao.getPermissionByCode(permissionCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> getAllPermission() {
        return dao.getAllPermission();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission insertPermission(Permission permission) {
        permission.setId(UUIDGenerator.getId());
        dao.insertPermission(permission);
        return dao.getPermissionById(permission.getId());
    }
}
