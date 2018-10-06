package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CategoryDao;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.IdGenerator;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category insertCategory(Category category) {
        category.setId(IdGenerator.getId());
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
    public boolean isExist(String id) {
        if (dao.isExist(id) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getTotalSize() {
        return dao.getTotalSize();
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
        dao.deleteCategoryById(id);
        return retCategory;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(Category category) {
        dao.updateCategory(category);
        return dao.getCategoryById(category.getId());
    }

}
