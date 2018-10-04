package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category insertCategory(Category category);

    boolean isExistName(String categoryName);

    boolean isExistCode(String categoryCode);

    Category getCategoryByCategoryCode(String categoryCode);

    List<Category> getAllCategory();

    Category deleteCategoryByCategoryCode(String categoryCode);

    Category updateCategory(Category category);
}
