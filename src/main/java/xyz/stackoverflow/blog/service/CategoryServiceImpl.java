package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CategoryDao;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 分类服务实现
 *
 * @author 凉衫薄
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> selectByPage(PageParameter pageParameter) {
        return dao.selectByPage(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category insert(Category category) {
        category.setId(UUIDGenerator.getId());
        dao.insert(category);
        return dao.selectById(category.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Category> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category deleteById(String id) {
        Category category = dao.selectById(id);
        dao.deleteById(id);
        return category;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category update(Category category) {
        dao.update(category);
        return dao.selectById(category.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Category> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category insertCategory(Category category) {
        category.setId(UUIDGenerator.getId());
        dao.insertCategory(category);
        return dao.getCategoryById(category.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistCode(String categoryCode) {
        if (dao.isExistCode(categoryCode) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistName(String categoryName) {
        if (dao.isExistName(categoryName) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getCategoryCount() {
        return dao.getCategoryCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category getCategoryByCode(String categoryCode) {
        return dao.getCategoryByCode(categoryCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category getCategoryById(String id) {
        return dao.getCategoryById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> getAllCategory() {
        return dao.getAllCategory();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> getLimitCategory(PageParameter pageParameter) {
        return dao.getLimitCategory(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category deleteCategoryById(String id) {
        Category retCategory = dao.getCategoryById(id);
        if (dao.deleteCategoryById(id) == 1) {
            return retCategory;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(Category category) {
        Category retCategory = dao.getCategoryById(category.getId());
        if (dao.updateCategory(category) == 1) {
            return retCategory;
        } else {
            return null;
        }
    }

}
