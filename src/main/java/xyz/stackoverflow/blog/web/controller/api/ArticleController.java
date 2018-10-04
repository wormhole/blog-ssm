package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.exception.IncorrectVCodeException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.util.ResponseJson;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ArticleController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getAllArticle() {
        ResponseJson response = new ResponseJson();
        List<Article> list = articleService.getAllArticle();
        response.setStatus(0);
        response.setMessage("获取成功");
        response.setData(list);
        return response;
    }

    @RequestMapping(value = "/article/{articlecode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getArticleByCode(@PathVariable("articlecode") String articleCode) {
        ResponseJson response = new ResponseJson();
        Article article = articleService.getArticleByCode(articleCode);
        if (article != null) {
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
