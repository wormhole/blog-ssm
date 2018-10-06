package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.util.ResponseJson;
import xyz.stackoverflow.blog.validator.CategoryValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class CategoryController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryValidator categoryValidator;

    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson insert(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();

        Map map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Map map1 = new HashMap<String, String>();
            Category category = categoryVO.toCategory();
            if (service.isExistName(categoryVO.getCategoryName())) {
                response.setStatus(FAILURE);
                response.setMessage("分类名已经存在");
                map1.put("name", "分类名重复");
                response.setData(map1);
            } else if (service.isExistCode(categoryVO.getCategoryCode())) {
                response.setStatus(FAILURE);
                response.setMessage("分类编码已经存在");
                map1.put("code", "分类编码重复");
                response.setData(map1);
            } else {
                service.insertCategory(category);
                response.setStatus(SUCCESS);
                response.setMessage("添加成功");
                response.setData(service.getAllCategory());
            }
        }
        return response;
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        ResponseJson response = new ResponseJson();
        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter();
            pageParameter.setPageNo(Integer.valueOf(page));
            pageParameter.setLimit(Integer.valueOf(limit));
            pageParameter.setStart((pageParameter.getPageNo() - 1) * pageParameter.getLimit());
            List<Category> list = service.getLimitCategory(pageParameter);
            int count = service.getTotalSize();

            Map map = new HashMap<String, Object>();
            map.put("count", count);
            map.put("items", list);
            response.setStatus(SUCCESS);
            response.setMessage("查询成功");
            response.setData(map);
        } else {
            List<Category> list = service.getAllCategory();
            int count = service.getTotalSize();

            Map map = new HashMap<String, Object>();
            map.put("count", count);
            map.put("items", list);
            response.setStatus(SUCCESS);
            response.setMessage("查询成功");
            response.setData(map);
        }
        return response;
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson delete(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();
        service.deleteCategoryById(categoryVO.getId());
        response.setStatus(SUCCESS);
        response.setMessage("删除成功");
        response.setData(service.getAllCategory());
        return response;
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson update(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();

        Map map = categoryValidator.validate(categoryVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Category oldCategory = service.getCategoryById(categoryVO.getId());
            Map map1 = new HashMap<String, String>();
            if (!oldCategory.getCategoryName().equals(categoryVO.getCategoryName()) && service.isExistName(categoryVO.getCategoryName())) {
                response.setStatus(FAILURE);
                response.setMessage("新分类名已经存在");
                map1.put("name", "分类名重复");
                response.setData(map1);
            } else if (!oldCategory.getCategoryCode().equals(categoryVO.getCategoryCode()) && service.isExistCode(categoryVO.getCategoryCode())) {
                response.setStatus(FAILURE);
                response.setMessage("新分类编码已经存在");
                map1.put("code", "分类编码重复");
                response.setData(map1);
            } else {
                service.updateCategory(categoryVO.toCategory());
                response.setStatus(SUCCESS);
                response.setMessage("更新成功");
                response.setData(service.getAllCategory());
            }
        }
        return response;
    }
}
