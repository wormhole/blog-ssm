package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.web.*;
import xyz.stackoverflow.blog.validator.CommentValidator;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论与点赞接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api")
public class CommentAndLikeController extends BaseController {

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
    public Response insertComment(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("comment", CommentVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("没有找到请求数据");
        }

        CommentVO commentVO = (CommentVO) voMap.get("comment").get(0);
        Map<String, String> map = commentValidator.validate(commentVO);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("评论字段格式错误", map);
        }

        Article article = articleService.getArticleByUrl(commentVO.getUrl());
        if (article == null) {
            throw new BusinessException("找不到该文章");
        }

        Comment comment = commentVO.toComment();
        comment.setDate(new Date());
        comment.setArticleId(article.getId());
        comment.setReview(0);
        commentService.insertComment(comment);

        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("评论成功");

        return response;
    }

    /**
     * 点赞接口 /api/like
     * 方法 POST
     *
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Response like(@RequestBody CommonDTO dto, HttpSession session) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("没有找到请求数据");
        }

        ArticleVO articleVO = (ArticleVO) voMap.get("article").get(0);
        Boolean isLike = (Boolean) session.getAttribute(articleVO.getUrl());

        if (isLike != null && !isLike) {
            Article article = articleService.getArticleByUrl(articleVO.getUrl());
            article.setLikes(article.getLikes() + 1);
            articleService.updateArticle(article);
            session.setAttribute(articleVO.getUrl(), true);
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("点赞成功");
            response.setData(article.getLikes());
        } else {
            throw new BusinessException("不能重复点赞");
        }

        return response;
    }
}
