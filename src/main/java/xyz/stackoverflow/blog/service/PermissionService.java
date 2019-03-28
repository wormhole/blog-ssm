package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;

import java.util.List;
import java.util.Map;

/**
 * 权限服务接口
 *
 * @author 凉衫薄
 */
public interface PermissionService {

    List<PermissionPO> selectByPage(Page page);

    List<PermissionPO> selectByCondition(Map<String, Object> searchMap);

    PermissionPO selectById(String id);

    PermissionPO insert(PermissionPO permission);

    int batchInsert(List<PermissionPO> list);

    PermissionPO deleteById(String id);

    int batchDeleteById(List<String> list);

    PermissionPO update(PermissionPO permission);

    int batchUpdate(List<PermissionPO> list);

}
