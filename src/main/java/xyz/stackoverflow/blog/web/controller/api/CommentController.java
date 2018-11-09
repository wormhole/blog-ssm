package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.util.AbstractVO;
import xyz.stackoverflow.blog.util.BaseController;
import xyz.stackoverflow.blog.util.BaseDTO;
import xyz.stackoverflow.blog.util.Response;
import xyz.stackoverflow.blog.validator.CommentValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论接口
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class CommentController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentValidator commentValidator;

    /**
     * 评论接口 /api/comment
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Response insertComment(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap,dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("没有找到请求数据");
        }
        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);

        Map<String, String> map = commentValidator.validate(commentVO);
        if (map.size() != 0) {
            throw new BusinessException("评论字段错误",map);
        } else {
            Article article = articleService.getArticleByUrl(commentVO.getUrl());
            Comment comment = commentVO.toComment();
            comment.setDate(new Date());
            comment.setArticleId(article.getId());
            comment.setReview(0);
            commentService.insertComment(comment);

            response.setStatus(SUCCESS);
            response.setMessage("评论成功");
        }
        return response;
    }
}
