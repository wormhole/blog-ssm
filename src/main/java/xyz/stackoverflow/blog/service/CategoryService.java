package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;

public interface CategoryService {

    Category insertCategory(Category category);
    Category getCategoryByName(String category);
}
