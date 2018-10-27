package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.CommentDao;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

/**
 * 评论服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment insertComment(Comment comment) {
        comment.setId(IdGenerator.getId());
        commentDao.insertComment(comment);
        return commentDao.getCommentById(comment.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> getCommentByArticleId(String articleId) {
        return commentDao.getCommentByArticleId(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment deleteCommentById(String id) {
        Comment comment = commentDao.getCommentById(id);
        commentDao.deleteCommentById(id);
        return comment;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> getAllComment() {
        return commentDao.getAllComment();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment getCommentById(String id) {
        return commentDao.getCommentById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getCommentCountByArticleId(String articleId) {
        return commentDao.getCommentCountByArticleId(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment commentReview(Comment comment) {
        commentDao.commentReview(comment);
        return commentDao.getCommentById(comment.getId());
    }
}
