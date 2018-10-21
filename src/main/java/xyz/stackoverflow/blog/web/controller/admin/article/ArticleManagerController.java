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
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.validator.ArticleValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理系统文章管理控制器
 *
 * @author 凉衫薄
 */
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

    /**
     * 获取文章 /admin/article/list
     * 方法 GET
     *
     * @param page 分页查询页码,允许为空,为空时查询所有文章
     * @param limit 每页的条目
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        ResponseVO response = new ResponseVO();
        List<Article> list = null;
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page),Integer.valueOf(limit),null);
            list = articleService.getLimitArticle(pageParameter);
        } else {
            list = articleService.getAllArticle();
        }

        int count = articleService.getArticleCount();
        List voList = new ArrayList<ArticleVO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (Article article : list) {
            ArticleVO vo = new ArticleVO();
            vo.setId(article.getId());
            vo.setTitle(article.getTitle());
            vo.setNickname(userService.getUserById(article.getUserId()).getNickname());
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setDateString(sdf.format(article.getDate()));
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

    /**
     * 文章删除 /admin/article/delete
     * 方法 POST
     *
     * @param articleVO 文章VO数组
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(@RequestBody ArticleVO[] articleVO) {
        ResponseVO response = new ResponseVO();
        for (ArticleVO vo : articleVO)
            articleService.deleteArticleById(vo.getId());
        response.setStatus(SUCCESS);
        response.setMessage("删除成功");
        return response;
    }

    /**
     * 获取单篇文章 /admin/article/get
     * 方法 POST
     *
     * @param articleVO 文章VO
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO get(@RequestBody ArticleVO articleVO) {
        ResponseVO response = new ResponseVO();
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

    /**
     * 更新文章 /admin/article/update
     * 方法 POST
     *
     * @param articleVO 文章VO
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@RequestBody ArticleVO articleVO) {
        ResponseVO response = new ResponseVO();
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
                map.put("url", "URL重复");
                response.setData(map);
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
