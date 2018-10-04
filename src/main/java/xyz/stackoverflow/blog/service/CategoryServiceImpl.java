package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CategoryDao;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.IdGenerator;

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
        return dao.getCategoryByCategoryCode(category.getCategoryCode());
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
    public Category getCategoryByCategoryCode(String categoryCode) {
        return dao.getCategoryByCategoryCode(categoryCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> getAllCategory() {
        return dao.getAllCategory();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category deleteCategoryByCategoryCode(String categoryCode) {
        Category retCategory = dao.getCategoryByCategoryCode(categoryCode);
        dao.deleteCategoryByCategoryCode(categoryCode);
        return retCategory;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(Category category) {
        dao.updateCategory(category);
        return dao.getCategoryByCategoryCode(category.getCategoryCode());
    }

}
