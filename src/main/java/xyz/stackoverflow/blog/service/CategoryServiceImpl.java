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
        return dao.getCategoryByName(category.getCategory());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category getCategoryByName(String category) {
        return dao.getCategoryByName(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Category> getAllCategory() {
        return dao.getAllCategory();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category deleteCategoryByName(String category) {
        Category retCategory = dao.getCategoryByName(category);
        if (retCategory == null) {
            return null;
        } else {
            dao.deleteCategoryByName(category);
            return retCategory;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(String category, String newCategory) {
        dao.updateCategory(category, newCategory);
        return dao.getCategoryByName(newCategory);
    }
}
