package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.io.Serializable;

public class CategoryVO implements Serializable {

    private String categoryName;
    private String categoryCode;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Category toCategory(){
        Category category = new Category();
        category.setCategoryCode(categoryCode);
        category.setCategoryName(categoryName);
        return category;
    }
}
