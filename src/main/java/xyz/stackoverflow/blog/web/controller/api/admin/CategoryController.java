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
import xyz.stackoverflow.blog.util.ValidationUtil;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.web.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

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
    private ArticleService articleService;
    @Autowired
    private ValidatorFactory validatorFactory;

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

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CategoryVO>> violations = validator.validate(categoryVO, CategoryVO.InsertGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Category category = categoryVO.toCategory();
        if (categoryService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryName", category.getCategoryName());
        }}).size() != 0) {
            throw new BusinessException("分类名已经存在");
        }
        if (categoryService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryCode", category.getCategoryCode());
        }}).size() != 0) {
            throw new BusinessException("分类编码已经存在");
        }

        category.setDeleteAble(1);
        categoryService.insert(category);
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

        Page page1 = new Page(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Category> list = categoryService.selectByPage(page1);
        int count = categoryService.selectByCondition(new HashMap<String, Object>()).size();

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

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CategoryVO>> violations = validator.validate(categoryVO, CategoryVO.DeleteGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Category category = categoryService.selectById(categoryVO.getId());

        if (category == null) {
            throw new BusinessException("未找到该分类");
        }
        if (category.getDeleteAble() == 0) {
            throw new BusinessException("该分类不允许删除");
        }

        Category unCategory = categoryService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryCode", "uncategory");
        }}).get(0);
        List<Article> articleList = articleService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryId", category.getId());
        }});
        for (Article article : articleList) {
            article.setCategoryId(unCategory.getId());
        }
        articleService.batchUpdate(articleList);
        categoryService.deleteById(category.getId());
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

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CategoryVO>> violations = validator.validate(categoryVO, CategoryVO.UpdateGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Category oldCategory = categoryService.selectById(categoryVO.getId());

        if (oldCategory == null) {
            throw new BusinessException("未找到该分类");
        }
        if (oldCategory.getDeleteAble() == 0) {
            throw new BusinessException("该分类不允许修改");
        }

        if (!oldCategory.getCategoryName().equals(categoryVO.getCategoryName()) && categoryService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryName", categoryVO.getCategoryName());
        }}).size() != 0) {
            throw new BusinessException("新分类名已经存在");
        }
        if (!oldCategory.getCategoryCode().equals(categoryVO.getCategoryCode()) && categoryService.selectByCondition(new HashMap<String, Object>() {{
            put("categoryCode", categoryVO.getCategoryCode());
        }}).size() != 0) {
            throw new BusinessException("新分类编码已经存在");
        }

        categoryService.update(categoryVO.toCategory());
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("更新成功");

        return response;
    }
}
