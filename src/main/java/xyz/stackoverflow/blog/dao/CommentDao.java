package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 评论表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface CommentDao {

    List<Comment> selectByPage(Page page);

    List<Comment> selectByCondition(Map<String, Object> searchMap);

    Comment selectById(String id);

    int insert(Comment comment);

    int batchInsert(List<Comment> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Comment comment);

    int batchUpdate(List<Comment> list);

}
