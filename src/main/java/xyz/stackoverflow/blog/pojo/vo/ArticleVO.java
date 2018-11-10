package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.web.SuperVO;

import java.util.Date;

/**
 * 文章VO
 *
 * @author 凉衫薄
 */
public class ArticleVO implements SuperVO {

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
    private Integer visible;

    private String articleCode;
    private String author;
    private String categoryName;
    private String createDateString;
    private String modifyDateString;
    private String preview;
    private Integer commentCount;
    private String visibleTag;

    public ArticleVO() {

    }

    public ArticleVO(String id, String userId, String title, String articleMd, String articleHtml, String categoryId, Date createDate, Date modifyDate, Integer hits, Integer likes, String url, Integer visible, String articleCode, String author, String categoryName, String createDateString, String modifyDateString, String preview, Integer commentCount, String visibleTag) {
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
        this.visible = visible;
        this.articleCode = articleCode;
        this.author = author;
        this.categoryName = categoryName;
        this.createDateString = createDateString;
        this.modifyDateString = modifyDateString;
        this.preview = preview;
        this.commentCount = commentCount;
        this.visibleTag = visibleTag;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getVisibleTag() {
        return visibleTag;
    }

    public void setVisibleTag(String visibleTag) {
        this.visibleTag = visibleTag;
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
        article.setVisible(visible);
        return article;
    }
}
