package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category insertCategory(Category category);

    boolean isExist(String category);

    Category getCategoryByName(String category);

    List<Category> getAllCategory();

    Category deleteCategoryByName(String category);

    Category updateCategory(String category, String newCategory);
}
