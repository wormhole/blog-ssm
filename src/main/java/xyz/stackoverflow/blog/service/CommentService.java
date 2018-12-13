package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 评论服务接口
 *
 * @author 凉衫薄
 */
public interface CommentService {

    List<Comment> selectByPage(PageParameter pageParameter);

    List<Comment> selectByCondition(Map<String,String> searchMap);

    Comment selectById(String id);

    Comment insert(Comment comment);

    int batchInsert(List<Comment> list);

    Comment deleteById(String id);

    int batchDeleteById(List<String> list);

    Comment update(Comment comment);

    int batchUpdate(List<Comment> list);


    Comment insertComment(Comment comment);

    List<Comment> getCommentByArticleId(String articleId);

    Comment deleteCommentById(String id);

    int getCommentCountByArticleId(String articleId);

    int getCommentCount();

    Comment commentReview(Comment comment);

    List<Comment> getLimitComment(PageParameter parameter);

}
