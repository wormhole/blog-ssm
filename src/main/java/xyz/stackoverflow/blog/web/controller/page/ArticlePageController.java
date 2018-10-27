package xyz.stackoverflow.blog.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.validator.CommentValidator;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 前端文章页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
public class ArticlePageController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentValidator commentValidator;

    /**
     * 跳转到文章单页 /article/{year}/{month}/{day}/{articleCode}
     * 方法 GET
     *
     * @param year
     * @param month
     * @param day
     * @param articleCode
     * @return 返回ModelAndView, 查找成功时, 视图设置为文章视图, 否则设为404视图
     */
    @RequestMapping(value = "/article/{year}/{month}/{day}/{articleCode}", method = RequestMethod.GET)
    public ModelAndView article(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day, @PathVariable("articleCode") String articleCode, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String url = "/article/" + year + "/" + month + "/" + day + "/" + articleCode;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat commentSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User admin = userService.getAdmin();
        UserVO userVO = new UserVO();
        userVO.setNickname(HtmlUtils.htmlEscape(admin.getNickname()));
        userVO.setSignature(HtmlUtils.htmlEscape(admin.getSignature()));
        userVO.setHeadUrl(admin.getHeadUrl());

        Article article = articleService.getArticleByUrl(url);
        if (article != null) {
            article.setHits(article.getHits() + 1);
            articleService.updateArticle(article);

            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            articleVO.setNickname(HtmlUtils.htmlEscape(userService.getUserById(article.getUserId()).getNickname()));
            articleVO.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            articleVO.setCommentCount(commentService.getCommentCountByArticleId(article.getId()));
            articleVO.setHits(article.getHits());
            articleVO.setLikes(article.getLikes());
            articleVO.setCreateDateString(sdf.format(article.getCreateDate()));
            articleVO.setArticleMd(article.getArticleMd());

            List<Comment> commentList = commentService.getCommentByArticleId(article.getId());
            List<CommentVO> voList = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentVO commentVO = new CommentVO();
                commentVO.setNickname(HtmlUtils.htmlEscape(comment.getNickname()));
                commentVO.setDateString(commentSdf.format(comment.getDate()));
                commentVO.setContent(HtmlUtils.htmlEscape(comment.getContent()));
                if (comment.getReplyTo() != null) {
                    commentVO.setReplyTo(HtmlUtils.htmlEscape(comment.getReplyTo()));
                }
                if (comment.getWebsite() != null) {
                    commentVO.setWebsite(comment.getWebsite());
                } else {
                    commentVO.setWebsite("javascript:;");
                }
                voList.add(commentVO);
            }

            mv.addObject("user", userVO);
            mv.addObject("article", articleVO);
            mv.addObject("commentList", voList);
            mv.setViewName("/article");
        } else {
            mv.setStatus(HttpStatus.NOT_FOUND);
            mv.addObject("user", userVO);
            mv.setViewName("/error/404");
        }

        if (session.getAttribute("isLike") == null) {
            session.setAttribute("isLike", false);
        }
        Boolean isLike = (Boolean) session.getAttribute("isLike");
        mv.addObject("isLike", isLike);
        return mv;
    }

    /**
     * 点赞接口 /article/like
     * 方法 POST
     *
     * @param articleVO
     * @param session
     * @return
     */
    @RequestMapping(value = "/article/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO like(@RequestBody ArticleVO articleVO, HttpSession session) {
        ResponseVO response = new ResponseVO();

        Boolean isLike = (Boolean) session.getAttribute("isLike");
        if (isLike != null && !isLike) {
            Article article = articleService.getArticleByUrl(articleVO.getUrl());
            article.setLikes(article.getLikes() + 1);
            articleService.updateArticle(article);
            session.setAttribute("isLike", true);
            response.setStatus(SUCCESS);
            response.setMessage("点赞成功");
            response.setData(article.getLikes());
        } else {
            response.setStatus(FAILURE);
            response.setMessage("点赞失败");
        }
        return response;
    }

    @RequestMapping(value = "/article/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertComment(@RequestBody CommentVO commentVO) {
        ResponseVO response = new ResponseVO();

        Map<String, String> map = commentValidator.validate(commentVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("评论字段错误");
            response.setData(map);
        } else {
            Article article = articleService.getArticleByUrl(commentVO.getUrl());
            Comment comment = commentVO.toComment();
            comment.setDate(new Date());
            comment.setArticleId(article.getId());
            comment.setReview(0);
            commentService.insertComment(comment);

            response.setStatus(SUCCESS);
            response.setMessage("评论获取成功");
        }
        return response;
    }
}
