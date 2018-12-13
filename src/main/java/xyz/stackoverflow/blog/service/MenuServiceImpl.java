package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.MenuDao;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 菜单服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> selectByPage(PageParameter pageParameter) {
        return dao.selectByPage(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu insert(Menu menu) {
        menu.setId(UUIDGenerator.getId());
        dao.insert(menu);
        return dao.selectById(menu.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Menu> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu deleteById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu update(Menu menu) {
        dao.update(menu);
        return dao.selectById(menu.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Menu> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu insertMenu(Menu menu) {
        menu.setId(UUIDGenerator.getId());
        dao.insertMenu(menu);
        return dao.getMenuById(menu.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> getAllMenu() {
        return dao.getAllMenu();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> getLimitMenu(PageParameter pageParameter) {
        return dao.getLimitMenu(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu updateMenu(Menu menu) {
        Menu ret = dao.getMenuById(menu.getId());
        if (dao.updateMenu(menu) != 0) {
            return ret;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu deleteMenuById(String id) {
        Menu ret = dao.getMenuById(id);
        if (dao.deleteMenuById(id) != 0) {
            return ret;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getMenuCount() {
        return dao.getMenuCount();
    }
}
