package xyz.stackoverflow.blog.web.controller.admin.article;

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
import xyz.stackoverflow.blog.validator.ArticleValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class ArticleManagerController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleValidator articleValidator;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        ResponseJson response = new ResponseJson();
        List<Article> list = null;
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter();
            pageParameter.setPageNo(Integer.valueOf(page));
            pageParameter.setLimit(Integer.valueOf(limit));
            pageParameter.setStart((pageParameter.getPageNo() - 1) * pageParameter.getLimit());
            list = articleService.getLimitArticle(pageParameter);

        } else {
            list = articleService.getAllArticle();
        }
        int count = articleService.getArticleCount();
        List<ArticleVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (Article article : list) {
            ArticleVO vo = new ArticleVO();
            vo.setId(article.getId());
            vo.setTitle(article.getTitle());
            vo.setNickname(userService.getUserById(article.getUserId()).getNickname());
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setDate(sdf.format(article.getDate()));
            vo.setUrl(article.getUrl());
            voList.add(vo);
        }

        Map map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson delete(@RequestBody ArticleVO[] articleVO) {
        ResponseJson response = new ResponseJson();
        for (ArticleVO vo : articleVO)
            articleService.deleteArticleById(vo.getId());
        response.setStatus(SUCCESS);
        response.setMessage("删除成功");
        return response;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson get(@RequestBody ArticleVO articleVO) {
        ResponseJson response = new ResponseJson();
        Article article = articleService.getArticleById(articleVO.getId());
        ArticleVO vo = null;
        if (article != null) {
            vo = new ArticleVO();
            vo.setId(article.getId());
            vo.setCategoryId(article.getCategoryId());
            vo.setTitle(article.getTitle());
            vo.setUrl(article.getUrl());
            vo.setArticleMd(article.getArticleMd());
            response.setStatus(SUCCESS);
            response.setMessage("文章获取成功");
            response.setData(vo);
        } else {
            response.setStatus(FAILURE);
            response.setMessage("文章获取失败");
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson update(@RequestBody ArticleVO articleVO) {
        ResponseJson response = new ResponseJson();
        Article article = articleService.getArticleById(articleVO.getId());

        Map map = articleValidator.validate(articleVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段错误");
            response.setData(map);
        } else {
            if (!article.getUrl().equals(articleVO.getUrl()) && articleService.isExistUrl(articleVO.getUrl())) {
                response.setStatus(FAILURE);
                response.setMessage("URL重复");
                Map map1 = new HashMap<String, String>();
                map1.put("url", "URL重复");
                response.setData(map1);
            }else {
                Article updateArticle = articleVO.toArticle();
                articleService.updateArticle(updateArticle);
                response.setStatus(SUCCESS);
                response.setMessage("文章更新成功");
            }
        }
        return response;
    }
}
