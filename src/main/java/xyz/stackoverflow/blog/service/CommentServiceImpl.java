package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CommentDao;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 评论服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> selectByPage(Page page) {
        return dao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment insert(Comment comment) {
        comment.setId(UUIDGenerator.getId());
        dao.insert(comment);
        return dao.selectById(comment.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Comment> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment deleteById(String id) {
        Comment comment = dao.selectById(id);
        dao.deleteById(id);
        return comment;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment update(Comment comment) {
        dao.update(comment);
        return dao.selectById(comment.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Comment> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment insertComment(Comment comment) {
        comment.setId(UUIDGenerator.getId());
        dao.insertComment(comment);
        return dao.getCommentById(comment.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> getCommentByArticleId(String articleId) {
        return dao.getCommentByArticleId(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment deleteCommentById(String id) {
        Comment comment = dao.getCommentById(id);
        dao.deleteCommentById(id);
        return comment;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getCommentCountByArticleId(String articleId) {
        return dao.getCommentCountByArticleId(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getCommentCount() {
        return dao.getCommentCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment commentReview(Comment comment) {
        Comment comment1 = dao.getCommentById(comment.getId());
        dao.commentReview(comment);
        return comment1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> getLimitComment(Page parameter) {
        return dao.getLimitComment(parameter);
    }

}
