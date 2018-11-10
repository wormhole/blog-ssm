package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.web.*;
import xyz.stackoverflow.blog.validator.CategoryValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类管理接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryValidator categoryValidator;
    @Autowired
    private ArticleService articleService;

    /**
     * 新增分类 /api/admin/category/insert
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);
        Map<String, String> map = categoryValidator.validate(categoryVO);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段错误", map);
        }

        Category category = categoryVO.toCategory();
        if (categoryService.isExistName(categoryVO.getCategoryName())) {
            throw new BusinessException("分类名已经存在");
        }
        if (categoryService.isExistCode(categoryVO.getCategoryCode())) {
            throw new BusinessException("分类编码已经存在");
        }

        category.setDeleteAble(1);
        categoryService.insertCategory(category);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("分类添加成功");

        return response;
    }

    /**
     * 获取分类 /api/admin/category/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public Response list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Category> list = categoryService.getLimitCategory(pageParameter);
        int count = categoryService.getCategoryCount();

        List<CategoryVO> voList = new ArrayList<>();
        for (Category category : list) {
            CategoryVO vo = new CategoryVO();
            vo.setId(category.getId());
            vo.setCategoryName(category.getCategoryName());
            vo.setCategoryCode(category.getCategoryCode());
            if (category.getDeleteAble() == 0) {
                vo.setDeleteTag("否");
            } else {
                vo.setDeleteTag("是");
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 删除分类 /api/admin/category/delete
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);
        Category category = categoryService.getCategoryById(categoryVO.getId());

        if (category == null) {
            throw new BusinessException("未找到该分类");
        }
        if (category.getDeleteAble() == 0) {
            throw new BusinessException("该分类不允许删除");
        }

        Category unCategory = categoryService.getCategoryByCode("uncategory");
        List<Article> list = articleService.getAllArticle();
        for (Article article : list) {
            if (article.getCategoryId().equals(categoryVO.getId())) {
                article.setCategoryId(unCategory.getId());
                articleService.updateArticle(article);
            }
        }
        categoryService.deleteCategoryById(categoryVO.getId());
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("分类删除成功");

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
    public Response update(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("category", CategoryVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        CategoryVO categoryVO = (CategoryVO) voMap.get("category").get(0);
        Map<String, String> map = categoryValidator.validate(categoryVO);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式有误", map);
        }

        Category oldCategory = categoryService.getCategoryById(categoryVO.getId());
        if (!oldCategory.getCategoryName().equals(categoryVO.getCategoryName()) && categoryService.isExistName(categoryVO.getCategoryName())) {
            throw new BusinessException("新分类名已经存在");
        }
        if (!oldCategory.getCategoryCode().equals(categoryVO.getCategoryCode()) && categoryService.isExistCode(categoryVO.getCategoryCode())) {
            throw new BusinessException("新分类编码已经存在");
        }
        if (categoryService.updateCategory(categoryVO.toCategory()) != null) {
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("更新成功");
        } else {
            throw new BusinessException("该分类不允许修改或未找到该分类");
        }
        return response;
    }
}
