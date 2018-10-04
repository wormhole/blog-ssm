package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryDao {
    int insertCategory(Category category);

    Category getCategoryByCategoryCode(String categoryCode);

    int isExistName(String categoryName);

    int isExistCode(String categoryCode);

    List<Category> getAllCategory();

    int deleteCategoryByCategoryCode(String categoryCode);

    Category updateCategory(Category category);
}
