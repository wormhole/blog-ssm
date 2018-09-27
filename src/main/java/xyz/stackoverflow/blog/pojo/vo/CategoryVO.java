package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.io.Serializable;

public class CategoryVO implements Serializable {

    private String catetory;

    public String getCatetory() {
        return catetory;
    }

    public void setCatetory(String catetory) {
        this.catetory = catetory;
    }

    public Category toCatetory(){
        Category cat = new Category();
        cat.setCatetory(catetory);
        return cat;
    }
}
