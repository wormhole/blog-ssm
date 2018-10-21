package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Article;

import java.util.Date;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 文章VO
 */
public class ArticleVO extends Article {

    protected String articleCode;
    protected String nickname;
    protected String categoryName;
    protected String dateString;

    public ArticleVO() {

    }

    public ArticleVO(String id, String userId, String title, String articleMd, String articleHtml, String categoryId, Date date, String url, String articleCode, String nickname, String categoryName, String dateString) {
        super(id, userId, title, articleMd, articleHtml, categoryId, date, url);
        this.articleCode = articleCode;
        this.nickname = nickname;
        this.categoryName = categoryName;
        this.dateString = dateString;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
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

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Article toArticle() {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setArticleHtml(articleHtml);
        article.setArticleMd(articleMd);
        article.setDate(date);
        article.setUrl(url);
        article.setCategoryId(categoryId);
        article.setUserId(userId);
        return article;
    }
}
