package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.ResponseJson;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

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
}
