package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;

/**
 * 菜单服务接口
 *
 * @author 凉衫薄
 */
public interface MenuService {

    Menu insertMenu(Menu menu);

    List<Menu> getAllMenu();

    List<Menu> getLimitMenu(PageParameter pageParameter);

    Menu getMenuById(String id);

    Menu updateMenu(Menu menu);

    Menu deleteMenuById(String id);

    int getMenuCount();
}
