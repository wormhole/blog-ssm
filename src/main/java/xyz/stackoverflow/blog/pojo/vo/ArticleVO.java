package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Article;

import java.io.Serializable;
import java.util.Date;

public class ArticleVO implements Serializable {
    private String id;
    private String title;
    private String articleMd;
    private String articleHtml;
    private String categoryId;
    private String articleCode;
    private String nickname;
    private String categoryName;
    private String date;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article toArticle() {
        Article article = new Article();
        article.setId(id);
        article.setArticleHtml(articleHtml);
        article.setArticleMd(articleMd);
        article.setTitle(title);
        article.setDate(new Date());
        article.setUrl(url);
        article.setCategoryId(categoryId);
        return article;
    }
}
