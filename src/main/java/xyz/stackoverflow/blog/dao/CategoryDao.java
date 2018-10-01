package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryDao {
    int insertCategory(Category category);

    Category getCategoryByName(String category);

    List<Category> getAllCategory();

    int deleteCategoryByName(String category);

    Category updateCategory(String category, String newCategory);
}
