package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.io.Serializable;

public class CategoryVO implements Serializable {

    private String id;
    private String categoryName;
    private String categoryCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        category.setId(id);
        category.setCategoryCode(categoryCode);
        category.setCategoryName(categoryName);
        return category;
    }
}
