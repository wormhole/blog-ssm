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
import xyz.stackoverflow.blog.util.ResponseJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getArticleList(@RequestParam(value = "page", required = false) String page) {
        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String, Object>();
        List<Article> list = null;
        List<ArticleVO> voList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (page != null) {
            PageParameter parameter = new PageParameter();
            parameter.setPageNo(Integer.valueOf(page));
            parameter.setLimit(5);
            parameter.setStart((parameter.getPageNo() - 1) * parameter.getLimit());
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
            vo.setDate(sdf.format(article.getDate()));
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

    @RequestMapping(value = "/article/{articlecode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getArticleByCode(@PathVariable("articlecode") String articleCode) {
        ResponseJson response = new ResponseJson();
        Article article = articleService.getArticleByCode(articleCode);
        if (article == null) {
            response.setStatus(FAILURE);
            response.setMessage("访问文章不存在");
        } else {
            response.setStatus(SUCCESS);
            response.setMessage("查找成功");
            response.setData(article);
        }
        return response;
    }
}
