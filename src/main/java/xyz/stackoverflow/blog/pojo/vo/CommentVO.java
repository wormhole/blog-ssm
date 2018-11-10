package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.web.SuperVO;

import java.util.Date;

/**
 * 评论VO类
 *
 * @author 凉衫薄
 */
public class CommentVO implements SuperVO {

    private String id;
    private String nickname;
    private String email;
    private String website;
    private String content;
    private String articleId;
    private Date date;
    private String replyTo;
    private Integer review;

    private String dateString;
    private String url;
    private String articleTitle;
    private String reviewTag;

    public CommentVO() {

    }

    public CommentVO(String id, String nickname, String email, String website, String content, String articleId, Date date, String replyTo, Integer review, String dateString, String url, String articleTitle, String reviewTag) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.website = website;
        this.content = content;
        this.articleId = articleId;
        this.date = date;
        this.replyTo = replyTo;
        this.review = review;
        this.dateString = dateString;
        this.url = url;
        this.articleTitle = articleTitle;
        this.reviewTag = reviewTag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getReviewTag() {
        return reviewTag;
    }

    public void setReviewTag(String reviewTag) {
        this.reviewTag = reviewTag;
    }

    /**
     * 转化成Comment实体类
     *
     * @return
     */
    public Comment toComment(){
        Comment comment = new Comment();
        comment.setId(id);
        comment.setEmail(email);
        comment.setNickname(nickname);
        comment.setWebsite(website);
        comment.setContent(content);
        comment.setDate(date);
        comment.setArticleId(articleId);
        comment.setReplyTo(replyTo);
        comment.setReview(review);
        return comment;
    }
}
