package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 分类服务接口
 *
 * @author 凉衫薄
 */
public interface CategoryService {

    List<Category> selectByPage(PageParameter pageParameter);

    List<Category> selectByCondition(Map<String, String> searchMap);

    Category selectById(String id);

    Category insert(Category category);

    int batchInsert(List<Category> list);

    Category deleteById(String id);

    int batchDeleteById(List<String> list);

    Category update(Category category);

    int batchUpdate(List<Category> list);


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
