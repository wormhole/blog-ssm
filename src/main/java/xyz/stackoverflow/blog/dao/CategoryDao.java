package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryDao {
    int insertCategory(Category category);

    Category getCategoryById(String id);

    Category getCategoryByCode(String categoryCode);

    int isExistName(String categoryName);

    int isExistCode(String categoryCode);

    int isExist(String id);

    List<Category> getAllCategory();

    int deleteCategoryById(String id);

    Category updateCategory(Category category);
}
