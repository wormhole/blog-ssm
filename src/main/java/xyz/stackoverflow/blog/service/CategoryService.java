package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;

/**
 * 分类服务接口
 *
 * @author 凉衫薄
 */
public interface CategoryService {

    Category insertCategory(Category category);

    boolean isExistName(String categoryName);

    boolean isExistCode(String categoryCode);

    int getCategoryCount();

    Category getCategoryByCode(String categoryCode);

    Category getCategoryById(String id);

    List<Category> getAllCategory();

    List<Category> getLimitCategory(PageParameter pageParameter);

    Category deleteCategoryById(String id);

    Category updateCategory(Category category);
}
