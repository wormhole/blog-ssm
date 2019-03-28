package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.dao.PermissionDao;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;

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
    public List<PermissionPO> selectByPage(Page page) {
        return dao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PermissionPO> selectByCondition(Map<String, Object> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionPO selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionPO insert(PermissionPO permission) {
        dao.insert(permission);
        return dao.selectById(permission.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<PermissionPO> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PermissionPO deleteById(String id) {
        PermissionPO permission = dao.selectById(id);
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
    public PermissionPO update(PermissionPO permission) {
        dao.update(permission);
        return dao.selectById(permission.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<PermissionPO> list) {
        return dao.batchUpdate(list);
    }

}
