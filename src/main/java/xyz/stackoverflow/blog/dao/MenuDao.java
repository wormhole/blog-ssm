package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.MenuPO;

import java.util.List;
import java.util.Map;

/**
 * menu表DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface MenuDao {

    List<MenuPO> selectByPage(Page page);

    List<MenuPO> selectByCondition(Map<String, Object> searchMap);

    MenuPO selectById(String id);

    int insert(MenuPO menu);

    int batchInsert(List<MenuPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(MenuPO menu);

    int batchUpdate(List<MenuPO> list);

}
