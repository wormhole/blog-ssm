package xyz.stackoverflow.blog.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.web.SuperVO;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 文章VO
 *
 * @author 凉衫薄
 */
public class ArticleVO implements SuperVO {

    @NotNull(message = "缺少主键字段", groups = {UpdateGroup.class, DeleteGroup.class, VisibleGroup.class})
    private String id;

    private String userId;

    @NotNull(message = "缺少标题字段", groups = {UpdateGroup.class, InsertGroup.class})
    @Length(min = 1, max = 20, message = "标题长度只能在1到20之内", groups = {UpdateGroup.class, InsertGroup.class})
    private String title;

    @NotNull(message = "缺少文章md字段", groups = {UpdateGroup.class, InsertGroup.class})
    @Length(min = 1, message = "文章长度要大于等于1", groups = {UpdateGroup.class, InsertGroup.class})
    private String articleMd;

    @NotNull(message = "缺少文章html字段", groups = {UpdateGroup.class, InsertGroup.class})
    @Length(min = 1, message = "文章长度要大于等于1", groups = {UpdateGroup.class, InsertGroup.class})
    private String articleHtml;

    @NotNull(message = "缺少分类字段", groups = {UpdateGroup.class, InsertGroup.class})
    private String categoryId;

    @NotNull(message = "缺少可视化标志字段", groups = {VisibleGroup.class})
    @DecimalMax(value = "1", message = "可视化标志只能为1或0", groups = {VisibleGroup.class})
    @DecimalMin(value = "0", message = "可视化标志只能为1或0", groups = {VisibleGroup.class})
    private Integer visible;

    @NotNull(message = "缺少url字段", groups = {LikeGroup.class})
    private String url;

    private Date createDate;
    private Date modifyDate;
    private Integer hits;
    private Integer likes;

    //以下为扩展字段
    @NotNull(message = "缺少文章编码字段", groups = {UpdateGroup.class, InsertGroup.class})
    @Length(min = 1, max = 20, message = "编码长度只能在1到20之间", groups = {UpdateGroup.class, InsertGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "编码只能为字母数字下划线", groups = {UpdateGroup.class, InsertGroup.class})
    private String articleCode;

    private String preview;
    private String author;
    private String categoryName;
    private Integer commentCount;
    private String visibleTag;

    public interface UpdateGroup {
    }

    public interface InsertGroup {
    }

    public interface DeleteGroup {
    }

    public interface VisibleGroup {
    }

    public interface LikeGroup {
    }

    public ArticleVO() {

    }

    public ArticleVO(String id, String userId, String title, String articleMd, String articleHtml, String categoryId, Date createDate, Date modifyDate, Integer hits, Integer likes, String url, Integer visible, String articleCode, String author, String categoryName, String preview, Integer commentCount, String visibleTag) {
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
