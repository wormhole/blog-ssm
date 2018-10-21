package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.pojo.entity.Permission;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 权限服务实现
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao dao;

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
        permission.setId(IdGenerator.getId());
        dao.insertPermission(permission);
        return dao.getPermissionById(permission.getId());
    }
}
