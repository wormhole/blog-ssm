package xyz.stackoverflow.blog.pojo.vo;

import org.hibernate.validator.constraints.Length;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.web.SuperVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 文章VO
 *
 * @author 凉衫薄
 */
public class ArticleVO implements SuperVO {

    private String id;
    private String userId;

    @NotNull(message = "标题不能为空")
    @Length(min = 1, max = 20, message = "长度只能在1到20之内")
    private String title;

    @NotNull(message = "文章不能为空")
    @Length(min = 1, message = "文章长度要大于等于1")
    private String articleMd;

    @NotNull(message = "文章不能为空")
    @Length(min = 1, message = "文章长度要大于等于1")
    private String articleHtml;

    @NotNull(message = "分类不能为空")
    private String categoryId;

    private Date createDate;
    private Date modifyDate;
    private Integer hits;
    private Integer likes;
    private String url;
    private Integer visible;


    @NotNull(message = "文章编码不能为空")
    @Length(min = 1, max = 20, message = "编码长度只能在1到20之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "编码只能为字母数字下划线")
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
