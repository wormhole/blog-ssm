package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Article;

import java.util.Date;

/**
 * 文章VO
 *
 * @author 凉衫薄
 */
public class ArticleVO extends Article {

    protected String articleCode;
    protected String nickname;
    protected String categoryName;
    protected String createDateString;
    protected String modifyDateString;
    protected String preview;
    protected Integer commentCount;
    protected String hiddenTag;

    public ArticleVO() {

    }

    public ArticleVO(String id, String userId, String title, String articleMd, String articleHtml, String categoryId, Date createDate, Date modifyDate, Integer hits, Integer likes, String url, Integer hidden, String articleCode, String nickname, String categoryName, String createDateString, String modifyDateString, String preview, Integer commentCount, String hiddenTag) {
        super(id, userId, title, articleMd, articleHtml, categoryId, createDate, modifyDate, hits, likes, url, hidden);
        this.articleCode = articleCode;
        this.nickname = nickname;
        this.categoryName = categoryName;
        this.createDateString = createDateString;
        this.modifyDateString = modifyDateString;
        this.preview = preview;
        this.commentCount = commentCount;
        this.hiddenTag = hiddenTag;
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

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public String getModifyDateString() {
        return modifyDateString;
    }

    public void setModifyDateString(String modifyDateString) {
        this.modifyDateString = modifyDateString;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getHiddenTag() {
        return hiddenTag;
    }

    public void setHiddenTag(String hiddenTag) {
        this.hiddenTag = hiddenTag;
    }

    /**
     * 转换成实体类
     *
     * @return 转换后的实体类
     */
    public Article toArticle() {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setArticleHtml(articleHtml);
        article.setArticleMd(articleMd);
        article.setCreateDate(createDate);
        article.setModifyDate(modifyDate);
        article.setUrl(url);
        article.setCategoryId(categoryId);
        article.setUserId(userId);
        article.setHits(hits);
        article.setLikes(likes);
        article.setHidden(hidden);
        return article;
    }
}
