package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.ValidationUtil;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.web.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 评论管理接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ValidatorFactory validatorFactory;

    /**
     * 获取评论列表接口 /api/admin/comment/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/comment/list", method = RequestMethod.GET)
    public Response list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        Page page1 = new Page(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Comment> list = commentService.selectByPage(page1);
        int count = commentService.selectByCondition(new HashMap<String, Object>()).size();
        List<CommentVO> voList = new ArrayList<>();

        for (Comment comment : list) {
            CommentVO vo = new CommentVO();
            vo.setId(comment.getId());
            vo.setNickname(HtmlUtils.htmlEscape(comment.getNickname()));
            vo.setEmail(HtmlUtils.htmlEscape(comment.getEmail()));
            vo.setWebsite(comment.getWebsite());
            vo.setDate(comment.getDate());
            vo.setContent(HtmlUtils.htmlEscape(comment.getContent()));
            vo.setArticleTitle(HtmlUtils.htmlEscape(articleService.selectById(comment.getArticleId()).getTitle()));
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
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 删除评论接口 /api/admin/comment/delete
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CommentVO>> violations = validator.validate(commentVO, CommentVO.DeleteGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式出错", map);
        }

        if (commentService.deleteById(commentVO.getId()) != null) {
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("评论删除成功");
        } else {
            throw new BusinessException("评论删除失败,找不到该评论");
        }

        return response;
    }

    /**
     * 审核评论接口 /api/admin/comment/review
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comment/review", method = RequestMethod.POST)
    public Response review(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CommentVO>> violations = validator.validate(commentVO, CommentVO.ReviewGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式出错", map);
        }

        Comment comment = commentVO.toComment();

        if (commentService.update(comment) != null) {
            response.setStatus(StatusConst.SUCCESS);
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
