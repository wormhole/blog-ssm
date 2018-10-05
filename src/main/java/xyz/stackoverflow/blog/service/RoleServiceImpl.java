package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getRoleById(String id) {
        return dao.getRoleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getRoleByCode(String roleCode) {
        return dao.getRoleByCode(roleCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getAllRole() {
        return dao.getAllRole();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insertRole(Role role) {
        role.setId(IdGenerator.getId());
        dao.insertRole(role);
        return dao.getRoleById(role.getId());
    }
}
