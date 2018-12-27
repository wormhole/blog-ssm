package xyz.stackoverflow.blog.web.controller.page.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Comment;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.CommentVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 前端文章页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
public class ArticlePageController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;

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

        Article article = articleService.selectByUrl(url);
        if (article != null) {
            article.setHits(article.getHits() + 1);
            articleService.update(article);

            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            articleVO.setAuthor(HtmlUtils.htmlEscape(userService.selectById(article.getUserId()).getNickname()));
            articleVO.setCategoryName(categoryService.selectById(article.getCategoryId()).getName());
            articleVO.setCommentCount(commentService.selectByCondition(new HashMap<String, Object>() {{
                put("articleId", article.getId());
            }}).size());
            articleVO.setHits(article.getHits());
            articleVO.setLikes(article.getLikes());
            articleVO.setCreateDate(article.getCreateDate());
            articleVO.setArticleMd(article.getArticleMd());

            List<Comment> commentList = commentService.selectByCondition(new HashMap<String, Object>() {{
                put("articleId", article.getId());
            }});
            List<CommentVO> voList = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentVO commentVO = new CommentVO();
                commentVO.setNickname(HtmlUtils.htmlEscape(comment.getNickname()));
                commentVO.setDate(comment.getDate());
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

            mv.addObject("article", articleVO);
            mv.addObject("commentList", voList);
            mv.setViewName("/article");
        } else {
            mv.setStatus(HttpStatus.NOT_FOUND);
            mv.setViewName("/error/404");
        }

        if (session.getAttribute(url) == null) {
            session.setAttribute(url, false);
        }
        Boolean isLike = (Boolean) session.getAttribute(url);
        mv.addObject("isLike", isLike);
        return mv;
    }
}
