package xyz.stackoverflow.blog.web.controller.front;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.PageParameter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
public class IndexPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 进入主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        ModelAndView mv = new ModelAndView();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        User admin = userService.getAdmin();
        UserVO userVO = new UserVO();
        userVO.setNickname(HtmlUtils.htmlEscape(admin.getNickname()));
        userVO.setSignature(HtmlUtils.htmlEscape(admin.getSignature()));
        userVO.setHeadUrl(admin.getHeadUrl());

        PageParameter parameter = new PageParameter(Integer.valueOf(page), 5, null);
        List<Article> articleList = articleService.getLimitArticle(parameter);
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleVO vo = new ArticleVO();
            vo.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            vo.setNickname(HtmlUtils.htmlEscape(userService.getUserById(article.getUserId()).getNickname()));
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setHits(article.getHits());
            vo.setUrl(article.getUrl());
            vo.setCreateDateString(sdf.format(article.getCreateDate()));
            vo.setPreview(Jsoup.parse(article.getArticleHtml()).text());
            articleVOList.add(vo);
        }

        int count = articleService.getArticleCount();
        int pageCount = (count % 5 == 0) ? count / 5 : count / 5 + 1;
        int start = (Integer.valueOf(page) - 2 < 1) ? 1 : Integer.valueOf(page) - 2;
        int end = (start + 4 > pageCount) ? pageCount : start + 4;
        if ((end - start) < 4) {
            start = (end - 4 < 1) ? 1 : end - 4;
        }

        mv.addObject("user", userVO);
        mv.addObject("articleList", articleVOList);
        mv.addObject("start", start);
        mv.addObject("end", end);
        mv.addObject("page", Integer.valueOf(page));
        mv.addObject("pageCount", pageCount);
        mv.addObject("path", "/");

        mv.setViewName("/index");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root(@RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        return index(page);
    }
}
