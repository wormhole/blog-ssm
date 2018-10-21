package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端接口文章控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class ArticleController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 文章获取 /api/article
     * 方法 GET
     *
     * @param page 分页参数,允许为空,为空时获取所有文章
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO list(@RequestParam(value = "page", required = false) Integer page) {
        ResponseVO response = new ResponseVO();
        Map map = new HashMap<String, Object>();
        List<Article> list = null;
        List voList = new ArrayList<ArticleVO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (page != null) {
            PageParameter parameter = new PageParameter(Integer.valueOf(page), 5, null);
            list = articleService.getLimitArticle(parameter);
            map.put("page", page);
        } else {
            list = articleService.getAllArticle();
        }
        for (Article article : list) {
            ArticleVO vo = new ArticleVO();
            vo.setTitle(article.getTitle());
            vo.setArticleHtml(article.getArticleHtml());
            vo.setNickname(userService.getUserById(article.getUserId()).getNickname());
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setDateString(sdf.format(article.getDate()));
            vo.setUrl(article.getUrl());
            voList.add(vo);
        }
        int count = articleService.getArticleCount();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(map);
        return response;
    }

    /**
     * 访问单篇文章 /api/article/{year}/{month}/{day}/{articleCode}
     * 方法 GET
     *
     * @param year
     * @param month
     * @param day
     * @param articleCode
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/article/{year}/{month}/{day}/{articleCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getArticleByCode(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day, @PathVariable("articleCode") String articleCode) {
        ResponseVO response = new ResponseVO();
        String url = "/article/" + year + "/" + month + "/" + day + "/" + articleCode;
        Article article = articleService.getArticleByUrl(url);
        if (article == null) {
            response.setStatus(FAILURE);
            response.setMessage("访问文章不存在");
        } else {
            ArticleVO vo = new ArticleVO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            vo.setTitle(article.getTitle());
            vo.setArticleMd(article.getArticleMd());
            vo.setNickname(userService.getUserById(article.getUserId()).getNickname());
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setDateString(sdf.format(article.getDate()));
            response.setStatus(SUCCESS);
            response.setMessage("查找成功");
            response.setData(vo);
        }
        return response;
    }
}
