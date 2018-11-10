package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * 分类实体类
 *
 * @author 凉衫薄
 */
public class Category implements Serializable {
    private String id;
    private String categoryName;
    private String categoryCode;
    private Integer deleteAble;

    public Category(){

    }

    public Category(String id, String categoryName, String categoryCode, Integer deleteAble) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.deleteAble = deleteAble;
    }

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

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }
}
