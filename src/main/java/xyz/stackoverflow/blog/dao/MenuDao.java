package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Menu;

import java.util.List;

/**
 * menu表DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface MenuDao {

    int insertMenu(Menu menu);

    List<Menu> getAllMenu();

    List<Menu> getLimitMenu(PageParameter pageParameter);

    Menu getMenuById(String id);

    int getMenuCount();

    int updateMenu(Menu menu);

    int deleteMenu(Menu menu);
}
