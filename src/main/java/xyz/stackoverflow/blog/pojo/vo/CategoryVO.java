package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.io.Serializable;

public class CategoryVO implements Serializable {

    private String category;
    private String newCategory;

    public String getCategory() {
        return category;
    }

    public void setCatetory(String category) {
        this.category = category;
    }

    public String getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public Category toCategory(){
        Category cat = new Category();
        cat.setCategory(category);
        return cat;
    }
}
