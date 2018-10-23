package xyz.stackoverflow.blog.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.UserService;

import java.text.SimpleDateFormat;

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
    public ModelAndView article(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day, @PathVariable("articleCode") String articleCode) {
        ModelAndView mv = new ModelAndView();
        String url = "/article/" + year + "/" + month + "/" + day + "/" + articleCode;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        User admin = userService.getAdmin();
        UserVO userVO = new UserVO();
        userVO.setNickname(HtmlUtils.htmlEscape(admin.getNickname()));
        userVO.setSignature(HtmlUtils.htmlEscape(admin.getSignature()));
        userVO.setHeadUrl(admin.getHeadUrl());

        Article article = articleService.getArticleByUrl(url);
        if (article != null) {
            article.setHits(article.getHits() + 1);
            articleService.updateArticle(article);

            ArticleVO vo = new ArticleVO();
            vo.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            vo.setNickname(HtmlUtils.htmlEscape(userService.getUserById(article.getUserId()).getNickname()));
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setHits(article.getHits());
            vo.setCreateDateString(sdf.format(article.getCreateDate()));
            vo.setArticleMd(article.getArticleMd());
            mv.addObject("user", userVO);
            mv.addObject("article", vo);
            mv.setViewName("/article");
        } else {
            mv.setStatus(HttpStatus.NOT_FOUND);
            mv.addObject("user", userVO);
            mv.setViewName("/error/404");
        }
        return mv;
    }
}
