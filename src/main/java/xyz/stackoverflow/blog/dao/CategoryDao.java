package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Category;

@Repository
public interface CategoryDao {
    int insertCategory(Category category);
    Category getCategoryByName(String category);
}
