package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Comment;

import java.util.List;

/**
 * 评论服务接口
 *
 * @author 凉衫薄
 */
public interface CommentService {

    Comment insertComment(Comment comment);
    List<Comment> getCommentByArticleId(String articleId);
    Comment deleteCommentById(String id);
    List<Comment> getAllComment();
    Comment getCommentById(String id);
    int getCommentCountByArticleId(String articleId);
    Comment commentReview(Comment comment);
}
