package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 分类表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface CategoryDao {

    List<Category> selectByPage(Page page);

    List<Category> selectByCondition(Map<String, String> searchMap);

    Category selectById(String id);

    int insert(Category category);

    int batchInsert(List<Category> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Category category);

    int batchUpdate(List<Category> list);


    int insertCategory(Category category);

    Category getCategoryById(String id);

    Category getCategoryByCode(String categoryCode);

    int isExistName(String categoryName);

    int isExistCode(String categoryCode);

    int getCategoryCount();

    List<Category> getAllCategory();

    List<Category> getLimitCategory(Page page);

    int deleteCategoryById(String id);

    int updateCategory(Category category);
}
