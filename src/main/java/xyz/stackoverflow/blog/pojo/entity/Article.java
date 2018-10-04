package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String ArticleMd;
    private String ArticleHtml;
    private String categoryId;
    private Date date;
    private String ArticleCode;

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

    public String getArticleMd() {
        return ArticleMd;
    }

    public void setArticleMd(String articleMd) {
        ArticleMd = articleMd;
    }

    public String getArticleHtml() {
        return ArticleHtml;
    }

    public void setArticleHtml(String articleHtml) {
        ArticleHtml = articleHtml;
    }

    public String getArticleCode() {
        return ArticleCode;
    }

    public void setArticleCode(String articleCode) {
        ArticleCode = articleCode;
    }
}
