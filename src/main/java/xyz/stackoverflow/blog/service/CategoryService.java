package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category insertCategory(Category category);

    boolean isExistName(String categoryName);

    boolean isExistCode(String categoryCode);

    boolean isExist(String id);

    Category getCategoryByCode(String categoryCode);

    Category getCategoryById(String id);

    List<Category> getAllCategory();

    Category deleteCategoryById(String id);

    Category updateCategory(Category category);
}
