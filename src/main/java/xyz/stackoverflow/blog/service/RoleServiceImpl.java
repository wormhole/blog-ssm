package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.pojo.entity.Role;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<String> getRoleByUserId(String userId) {
        List<Role> roleList = dao.getRoleByUserId(userId);
        Set<String> retSet = null;
        if ((null != roleList) && (roleList.size() != 0)) {
            retSet = new HashSet();
            for (Role role : roleList) {
                retSet.add(role.getRole());
            }
        }
        return retSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insertUserRole(Role role) {
        role.setId(IdGenerator.getId());
        dao.insertUserRole(role);
        return role;
    }
}
