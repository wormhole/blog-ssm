package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.validator.CategoryValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理系统分类管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/article")
public class CategoryController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryValidator categoryValidator;
    @Autowired
    private ArticleService articleService;

    /**
     * 新增分类 /admin/article/category/insert
     * 方法 POST
     *
     * @param categoryVO
     * @return
     */
    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insert(@RequestBody CategoryVO categoryVO) {
        ResponseVO response = new ResponseVO();

        Map<String, String> map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Category category = categoryVO.toCategory();
            if (categoryService.isExistName(categoryVO.getCategoryName())) {
                response.setStatus(FAILURE);
                response.setMessage("分类名已经存在");
                map.put("name", "分类名重复");
                response.setData(map);
            } else if (categoryService.isExistCode(categoryVO.getCategoryCode())) {
                response.setStatus(FAILURE);
                response.setMessage("分类编码已经存在");
                map.put("code", "分类编码重复");
                response.setData(map);
            } else {
                category.setDeleteAble(1);
                categoryService.insertCategory(category);
                response.setStatus(SUCCESS);
                response.setMessage("添加成功");
                response.setData(categoryService.getAllCategory());
            }
        }
        return response;
    }

    /**
     * 获取分类 /admin/article/category/list
     * 方法GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        ResponseVO response = new ResponseVO();
        List<Category> list = null;
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
            list = categoryService.getLimitCategory(pageParameter);
        } else {
            list = categoryService.getAllCategory();
        }
        int count = categoryService.getCategoryCount();

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", list);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 删除分类 /admin/article/category/delete
     * 方法POST
     *
     * @param categoryVO
     * @return
     */
    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(@RequestBody CategoryVO categoryVO) {
        ResponseVO response = new ResponseVO();
        Category category = categoryService.getCategoryById(categoryVO.getId());
        if (category.getDeleteAble() == 0) {
            response.setStatus(FAILURE);
            response.setMessage("该分类不允许删除");
        } else {
            Category unCategory = categoryService.getCategoryByCode("uncategory");
            List<Article> list = articleService.getAllArticle();
            for (Article article : list) {
                if (article.getCategoryId().equals(categoryVO.getId())) {
                    article.setCategoryId(unCategory.getId());
                    articleService.updateArticle(article);
                }
            }
            categoryService.deleteCategoryById(categoryVO.getId());
            response.setStatus(SUCCESS);
            response.setMessage("删除成功");
            response.setData(categoryService.getAllCategory());
        }
        return response;
    }

    /**
     * 更新分类 /admin/article/category/update
     * 方法 POST
     *
     * @param categoryVO
     * @return
     */
    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@RequestBody CategoryVO categoryVO) {
        ResponseVO response = new ResponseVO();

        Map<String, String> map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Category oldCategory = categoryService.getCategoryById(categoryVO.getId());
            if (!oldCategory.getCategoryName().equals(categoryVO.getCategoryName()) && categoryService.isExistName(categoryVO.getCategoryName())) {
                response.setStatus(FAILURE);
                response.setMessage("新分类名已经存在");
                map.put("name", "分类名重复");
                response.setData(map);
            } else if (!oldCategory.getCategoryCode().equals(categoryVO.getCategoryCode()) && categoryService.isExistCode(categoryVO.getCategoryCode())) {
                response.setStatus(FAILURE);
                response.setMessage("新分类编码已经存在");
                map.put("code", "分类编码重复");
                response.setData(map);
            } else if (categoryService.updateCategory(categoryVO.toCategory()) != null) {
                response.setStatus(SUCCESS);
                response.setMessage("更新成功");
                response.setData(categoryService.getAllCategory());
            } else {
                response.setStatus(FAILURE);
                response.setMessage("更新失败");
                map.put("other", "该分类不允许修改或未找到该分类");
                response.setData(map);
            }
        }
        return response;
    }
}
