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
import xyz.stackoverflow.blog.util.ResponseJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getCategory() {
        ResponseJson response = new ResponseJson();
        List<Category> categoryList = categoryService.getAllCategory();
        List<CategoryVO> categoryVOList = new ArrayList();
        for (Category category : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryCode(category.getCategoryCode());
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setArticleNum(articleService.getArticleByCategoryId(category.getId()).size());
            categoryVOList.add(categoryVO);
        }
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(categoryVOList);
        return response;
    }

    @RequestMapping(value="/category/{categoryCode}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseJson getArticleByCategoryCode(@PathVariable("categoryCode") String categoryCode,@RequestParam(value = "page", required = false) Integer page){
        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String, Object>();
        List<Article> list = null;
        List<ArticleVO> voList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Category category = categoryService.getCategoryByCode(categoryCode);
        if (page != null) {
            PageParameter parameter = new PageParameter();
            parameter.setPageNo(page);
            parameter.setLimit(5);
            parameter.setStart((parameter.getPageNo() - 1) * parameter.getLimit());
            parameter.setWhere(category.getId());
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
            vo.setDate(sdf.format(article.getDate()));
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
