package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体类
 *
 * @author 凉衫薄
 */
public class Comment implements Serializable {

    protected String id;
    protected String nickname;
    protected String email;
    protected String website;
    protected String content;
    protected String articleId;
    protected Date date;
    protected Integer type;
    protected String replyTo;

    public Comment(){

    }

    public Comment(String id, String nickname, String email, String website, String content, String articleId, Date date, Integer type, String replyTo) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.website = website;
        this.content = content;
        this.articleId = articleId;
        this.date = date;
        this.type = type;
        this.replyTo = replyTo;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
