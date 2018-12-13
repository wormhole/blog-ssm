package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 评论表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface CommentDao {

    List<Comment> selectByPage(PageParameter pageParameter);

    List<Comment> selectByCondition(Map<String, String> searchMap);

    Comment selectById(String id);

    int insert(Comment comment);

    int batchInsert(List<Comment> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Comment comment);

    int batchUpdate(List<Comment> list);


    int insertComment(Comment comment);

    List<Comment> getCommentByArticleId(String articleId);

    int deleteCommentById(String id);

    Comment getCommentById(String id);

    int getCommentCountByArticleId(String articleId);

    int getCommentCount();

    int commentReview(Comment comment);

    List<Comment> getLimitComment(PageParameter parameter);

    int deleteCommentByArticleId(String articleId);
}
