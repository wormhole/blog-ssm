package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.MenuDao;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.util.UUIDGenerator;

import java.util.List;

/**
 * 菜单服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu insertMenu(Menu menu) {
        menu.setId(UUIDGenerator.getId());
        menuDao.insertMenu(menu);
        return menuDao.getMenuById(menu.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> getLimitMenu(PageParameter pageParameter) {
        return menuDao.getLimitMenu(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu getMenuById(String id) {
        return menuDao.getMenuById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu updateMenu(Menu menu) {
        if (menuDao.updateMenu(menu) != 0) {
            return menuDao.getMenuById(menu.getId());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu deleteMenu(Menu menu) {
        Menu ret = menuDao.getMenuById(menu.getId());
        if (menuDao.deleteMenu(menu) != 0) {
            return ret;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getMenuCount() {
        return menuDao.getMenuCount();
    }
}
