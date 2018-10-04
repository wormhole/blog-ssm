package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String blogMd;
    private String blogHtml;
    private String categoryId;
    private Date date;
    private String blogCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogMd() {
        return blogMd;
    }

    public void setBlogMd(String blogMd) {
        this.blogMd = blogMd;
    }

    public String getBlogHtml() {
        return blogHtml;
    }

    public void setBlogHtml(String blogHtml) {
        this.blogHtml = blogHtml;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBlogCode() {
        return blogCode;
    }

    public void setBlogCode(String blogCode) {
        this.blogCode = blogCode;
    }
}
