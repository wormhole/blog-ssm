package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 分类VO
 */
public class CategoryVO extends Category {

    protected Integer articleCount;

    public CategoryVO(){

    }

    public CategoryVO(String id, String categoryName, String categoryCode, Integer deleteAble, Integer articleCount) {
        super(id, categoryName, categoryCode, deleteAble);
        this.articleCount = articleCount;
    }

    public Category toCategory(){
        Category category = new Category();
        category.setId(id);
        category.setCategoryCode(categoryCode);
        category.setCategoryName(categoryName);
        category.setDeleteAble(deleteAble);
        return category;
    }
}
