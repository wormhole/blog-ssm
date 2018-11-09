package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 *
 * @author 凉衫薄
 */
public class Article implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String articleMd;
    private String articleHtml;
    private String categoryId;
    private Date createDate;
    private Date modifyDate;
    private Integer hits;
    private Integer likes;
    private String url;
    private Integer hidden;

    public Article(){

    }

    public Article(String id, String userId, String title, String articleMd, String articleHtml, String categoryId, Date createDate, Date modifyDate, Integer hits, Integer likes, String url, Integer hidden) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.articleMd = articleMd;
        this.articleHtml = articleHtml;
        this.categoryId = categoryId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.hits = hits;
        this.likes = likes;
        this.url = url;
        this.hidden = hidden;
    }

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

    public String getArticleMd() {
        return articleMd;
    }

    public void setArticleMd(String articleMd) {
        this.articleMd = articleMd;
    }

    public String getArticleHtml() {
        return articleHtml;
    }

    public void setArticleHtml(String articleHtml) {
        this.articleHtml = articleHtml;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }
}
