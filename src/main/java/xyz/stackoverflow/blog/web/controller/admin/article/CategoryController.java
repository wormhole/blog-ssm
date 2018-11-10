package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.*;
import xyz.stackoverflow.blog.validator.CategoryValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
public class CategoryController extends BaseController {

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
     * @param dto
     * @return
     */
    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    @ResponseBody
    public Response insert(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("找不到请求数据");
        }
        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);

        Map<String, String> map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            throw new BusinessException("字段错误", map);
        } else {
            Category category = categoryVO.toCategory();
            if (categoryService.isExistName(categoryVO.getCategoryName())) {
                map.put("name", "分类名重复");
                throw new BusinessException("分类名已经存在", map);
            } else if (categoryService.isExistCode(categoryVO.getCategoryCode())) {
                map.put("code", "分类编码重复");
                throw new BusinessException("分类编码已经存在", map);
            } else {
                category.setDeleteAble(1);
                categoryService.insertCategory(category);
                response.setStatus(SUCCESS);
                response.setMessage("添加成功");
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
    public Response list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        Response response = new Response();
        List<Category> list = null;
        List<CategoryVO> voList = new ArrayList<>();
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
            list = categoryService.getLimitCategory(pageParameter);
        } else {
            list = categoryService.getAllCategory();
        }
        int count = categoryService.getCategoryCount();

        for (Category category : list) {
            CategoryVO vo = new CategoryVO();
            vo.setId(category.getId());
            vo.setCategoryName(category.getCategoryName());
            vo.setCategoryCode(category.getCategoryCode());
            vo.setDeleteAble(category.getDeleteAble());
            if (category.getDeleteAble() == 0) {
                vo.setDeleteTag("否");
            }else{
                vo.setDeleteTag("是");
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 删除分类 /admin/article/category/delete
     * 方法POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("找不到请求数据");
        }
        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);

        Category category = categoryService.getCategoryById(categoryVO.getId());
        if (category.getDeleteAble() == 0) {
            throw new BusinessException("改分类不允许删除");
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
        }
        return response;
    }

    /**
     * 更新分类 /admin/article/category/update
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody BaseDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("找不到请求数据");
        }
        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);

        Map<String, String> map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Category oldCategory = categoryService.getCategoryById(categoryVO.getId());
            if (!oldCategory.getCategoryName().equals(categoryVO.getCategoryName()) && categoryService.isExistName(categoryVO.getCategoryName())) {
                map.put("name", "分类名重复");
                throw new BusinessException("新分类名已经存在", map);
            } else if (!oldCategory.getCategoryCode().equals(categoryVO.getCategoryCode()) && categoryService.isExistCode(categoryVO.getCategoryCode())) {
                map.put("code", "分类编码重复");
                throw new BusinessException("新分类编码已经存在", map);
            } else if (categoryService.updateCategory(categoryVO.toCategory()) != null) {
                response.setStatus(SUCCESS);
                response.setMessage("更新成功");
            } else {
                map.put("other", "该分类不允许修改或未找到该分类");
                throw new BusinessException("该分类不允许修改或未找到该分类", map);
            }
        }
        return response;
    }
}
