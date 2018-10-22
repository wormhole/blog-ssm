package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.service.ArticleService;

/**
 * 前端页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticlePageController {

    @Autowired
    private ArticleService articleService;

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
    @RequestMapping("/{year}/{month}/{day}/{articleCode}")
    public ModelAndView article(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day, @PathVariable("articleCode") String articleCode) {
        ModelAndView mv = new ModelAndView();
        String url = "/article/" + year + "/" + month + "/" + day + "/" + articleCode;
        Article article = articleService.getArticleByUrl(url);
        if (article != null) {
            article.setHits(article.getHits() + 1);
            articleService.updateArticle(article);
            mv.setViewName("/article");
        } else {
            mv.setStatus(HttpStatus.NOT_FOUND);
            mv.setViewName("/error/404");
        }
        return mv;
    }
}
