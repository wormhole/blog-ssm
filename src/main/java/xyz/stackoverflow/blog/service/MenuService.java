package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 菜单服务接口
 *
 * @author 凉衫薄
 */
public interface MenuService {

    List<Menu> selectByPage(Page page);

    List<Menu> selectByCondition(Map<String, String> searchMap);

    Menu selectById(String id);

    Menu insert(Menu menu);

    int batchInsert(List<Menu> list);

    Menu deleteById(String id);

    int batchDeleteById(List<String> list);

    Menu update(Menu menu);

    int batchUpdate(List<Menu> list);


    Menu insertMenu(Menu menu);

    List<Menu> getAllMenu();

    List<Menu> getLimitMenu(Page page);

    Menu updateMenu(Menu menu);

    Menu deleteMenuById(String id);

    int getMenuCount();
}
