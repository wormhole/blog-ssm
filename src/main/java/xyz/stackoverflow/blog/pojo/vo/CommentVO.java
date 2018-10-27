package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Comment;

import java.util.Date;

/**
 * 评论VO类
 *
 * @author 凉衫薄
 */
public class CommentVO extends Comment {

    protected String dateString;
    protected String url;
    protected String articleTitle;

    public CommentVO() {

    }

    public CommentVO(String id, String nickname, String email, String website, String content, String articleId, Date date, String replyTo, Integer review, String dateString, String url, String articleTitle) {
        super(id, nickname, email, website, content, articleId, date, replyTo, review);
        this.dateString = dateString;
        this.url = url;
        this.articleTitle = articleTitle;
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
