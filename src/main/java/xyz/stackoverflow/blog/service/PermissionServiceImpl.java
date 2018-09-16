package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.dao.RoleDao;
import xyz.stackoverflow.blog.pojo.entity.Permission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao dao;

    @Override
    public Set<String> getPermissionByRole(String role) {
        List<Permission> list = dao.getPermissionByRole(role);
        Set<String> retSet = null;
        if ((null != list) && (list.size() != 0)) {
            retSet = new HashSet();
            for (Permission permission : list) {
                retSet.add(permission.getPermission());
            }
        }
        return retSet;
    }

    @Override
    public Set<String> getAllPermission(String[] roles) {
        Set<String> retSet = new HashSet();
        for (String role : roles) {
            Set<String> set = getPermissionByRole(role);
            if (set != null) {
                retSet.addAll(set);
            }
        }
        if (retSet.isEmpty()) {
            return null;
        } else {
            return retSet;
        }
    }
}
