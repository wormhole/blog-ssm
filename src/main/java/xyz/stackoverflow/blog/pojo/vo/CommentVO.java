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

    public CommentVO() {

    }

    public CommentVO(String id, String nickname, String email, String website, String content, String articleId, Date date, Integer type, String replyTo, String dateString) {
        super(id, nickname, email, website, content, articleId, date, type, replyTo);
        this.dateString = dateString;
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
        comment.setType(type);
        comment.setReplyTo(replyTo);
        return comment;
    }
}
