package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

/**
 * 分类VO
 *
 * @author 凉衫薄
 */
public class CategoryVO extends Category {

    protected Integer articleCount;

    public CategoryVO(){

    }

    public CategoryVO(String id, String categoryName, String categoryCode, Integer deleteAble, Integer articleCount) {
        super(id, categoryName, categoryCode, deleteAble);
        this.articleCount = articleCount;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    /**
     * 转换成实体类
     *
     * @return 转换后的实体类
     */
    public Category toCategory(){
        Category category = new Category();
        category.setId(id);
        category.setCategoryCode(categoryCode);
        category.setCategoryName(categoryName);
        category.setDeleteAble(deleteAble);
        return category;
    }
}
