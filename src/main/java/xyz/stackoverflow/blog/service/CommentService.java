package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 评论服务接口
 *
 * @author 凉衫薄
 */
public interface CommentService {

    List<Comment> selectByPage(Page page);

    List<Comment> selectByCondition(Map<String, Object> searchMap);

    Comment selectById(String id);

    Comment insert(Comment comment);

    int batchInsert(List<Comment> list);

    Comment deleteById(String id);

    int batchDeleteById(List<String> list);

    Comment update(Comment comment);

    int batchUpdate(List<Comment> list);

}
