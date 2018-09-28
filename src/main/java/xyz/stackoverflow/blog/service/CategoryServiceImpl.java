package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CategoryDao;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.IdGenerator;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category insertCategory(Category category) {
        category.setId(IdGenerator.getId());
        return dao.getCategoryByName(category.getCatetory());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category getCategoryByName(String category) {
        return dao.getCategoryByName(category);
    }
}
