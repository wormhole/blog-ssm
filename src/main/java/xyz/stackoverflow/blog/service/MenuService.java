package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Menu;

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

    Menu deleteMenu(Menu menu);

    int getMenuCount();
}
