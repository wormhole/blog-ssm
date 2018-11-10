package xyz.stackoverflow.blog.web.controller.admin.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.util.*;

import java.lang.reflect.InvocationTargetException;
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
public class CommentManageController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        Response response = new Response();

        List<Comment> list = null;
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
            list = commentService.getLimitComment(pageParameter);
        } else {
            list = commentService.getAllComment();
        }

        int count = commentService.getCommentCount();
        List<CommentVO> voList = new ArrayList<>();

        for (Comment comment : list) {
            CommentVO vo = new CommentVO();
            vo.setId(comment.getId());
            vo.setNickname(HtmlUtils.htmlEscape(comment.getNickname()));
            vo.setEmail(HtmlUtils.htmlEscape(comment.getEmail()));
            vo.setWebsite(comment.getWebsite());
            vo.setDateString(DateUtil.formatDateTime(comment.getDate()));
            vo.setContent(HtmlUtils.htmlEscape(comment.getContent()));
            vo.setArticleTitle(HtmlUtils.htmlEscape(articleService.getArticleById(comment.getArticleId()).getTitle()));
            if (comment.getReview() == 0) {
                vo.setReviewTag("否");
            } else {
                vo.setReviewTag("是");
            }
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
    public Response delete(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("找不到请求数据");
        }
        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);

        if (commentService.deleteCommentById(commentVO.getId()) != null) {
            response.setStatus(SUCCESS);
            response.setMessage("评论删除成功");
        } else {
            throw new BusinessException("评论删除失败");
        }

        return response;
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    @ResponseBody
    public Response review(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("找不到请求数据");
        }
        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);

        Comment comment = commentVO.toComment();
        if (commentService.commentReview(comment) != null) {
            response.setStatus(SUCCESS);
            if (comment.getReview() == 1) {
                response.setMessage("审核成功");
            } else {
                response.setMessage("撤回成功");
            }
        } else {
            if (comment.getReview() == 1) {
                throw new BusinessException("审核失败");
            } else {
                throw new BusinessException("撤回失败");
            }
        }
        return response;
    }
}
