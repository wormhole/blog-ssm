package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
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
 * 前端接口分类控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class CategoryController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    /**
     * 分类获取 /api/category
     * 方法 GET
     *
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCategory() {
        ResponseVO response = new ResponseVO();
        List<Category> categoryList = categoryService.getAllCategory();
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryCode(category.getCategoryCode());
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setArticleCount(articleService.getArticleByCategoryId(category.getId()).size());
            categoryVOList.add(categoryVO);
        }
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(categoryVOList);
        return response;
    }

    /**
     * 某一分类文章获取 /api/category/{categoryCode}
     * 方法 GET
     *
     * @param categoryCode 分类编码
     * @param page         分页查询参数,允许为空,为空时返回所有该分类文章
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/category/{categoryCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getArticleByCategoryCode(@PathVariable("categoryCode") String categoryCode, @RequestParam(value = "page", required = false) Integer page) {
        ResponseVO response = new ResponseVO();
        Map<String,Object> map = new HashMap<>();
        List<Article> list = null;
        List<ArticleVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Category category = categoryService.getCategoryByCode(categoryCode);
        if (page != null) {
            PageParameter parameter = new PageParameter(page, 5, category.getId());
            list = articleService.getLimitArticleByCategoryId(parameter);
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
        int count = articleService.getArticleCountByCategoryId(category.getId());
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(map);
        return response;
    }
}
