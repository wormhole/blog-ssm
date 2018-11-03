package xyz.stackoverflow.blog.web.controller.admin.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.pojo.PageParameter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentManageController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        ResponseVO response = new ResponseVO();

        List<Comment> list = null;
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
            list = commentService.getLimitComment(pageParameter);
        } else {
            list = commentService.getAllComment();
        }

        int count = commentService.getCommentCount();
        List<CommentVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Comment comment : list) {
            CommentVO vo = new CommentVO();
            vo.setId(comment.getId());
            vo.setNickname(HtmlUtils.htmlEscape(comment.getNickname()));
            vo.setEmail(HtmlUtils.htmlEscape(comment.getEmail()));
            vo.setWebsite(comment.getWebsite());
            vo.setDateString(sdf.format(comment.getDate()));
            vo.setContent(HtmlUtils.htmlEscape(comment.getContent()));
            vo.setArticleTitle(HtmlUtils.htmlEscape(articleService.getArticleById(comment.getArticleId()).getTitle()));
            vo.setReview(comment.getReview());
            if (comment.getReplyTo() != null) {
                vo.setReplyTo(HtmlUtils.htmlEscape(comment.getReplyTo()));
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(@RequestBody CommentVO commentVO) {
        ResponseVO response = new ResponseVO();

        if (commentService.deleteCommentById(commentVO.getId()) != null) {
            response.setStatus(SUCCESS);
            response.setMessage("评论删除成功");
        } else {
            response.setStatus(FAILURE);
            response.setMessage("评论删除失败");
        }

        return response;
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO review(@RequestBody CommentVO commentVO) {
        ResponseVO response = new ResponseVO();

        Comment comment = commentVO.toComment();
        if (commentService.commentReview(comment) != null) {
            response.setStatus(SUCCESS);
            if (comment.getReview() == 1) {
                response.setMessage("审核成功");
            } else {
                response.setMessage("撤回成功");
            }
        } else {
            response.setStatus(FAILURE);
            if (comment.getReview() == 1) {
                response.setMessage("审核失败");
            } else {
                response.setMessage("撤回失败");
            }
        }
        return response;
    }
}
