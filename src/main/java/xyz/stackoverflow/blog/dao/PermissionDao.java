package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.PermissionPO;

import java.util.List;
import java.util.Map;

/**
 * 权限表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface PermissionDao {

    List<PermissionPO> selectByPage(Page page);

    List<PermissionPO> selectByCondition(Map<String, Object> searchMap);

    PermissionPO selectById(String id);

    int insert(PermissionPO permission);

    int batchInsert(List<PermissionPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(PermissionPO permission);

    int batchUpdate(List<PermissionPO> list);

}
