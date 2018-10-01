package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.ResponseJson;

@Controller
@RequestMapping("/admin/article")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson insert(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();
        Category category = categoryVO.toCategory();
        service.insertCategory(category);
        response.setStatus(0);
        response.setMessage("添加成功");
        response.setData(service.getAllCategory());
        return response;
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson list() {
        ResponseJson response = new ResponseJson();
        response.setStatus(0);
        response.setMessage("查询成功");
        response.setData(service.getAllCategory());
        return response;
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson delete(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();

        if(service.isExist(categoryVO.getCategory())){
            service.deleteCategoryByName(categoryVO.getCategory());
            response.setStatus(0);
            response.setMessage("删除成功");
            response.setData(service.getAllCategory());
        }else{
            response.setStatus(1);
            response.setMessage("未找到此分类");
        }

        return response;
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson update(@RequestBody CategoryVO categoryVO) {
        ResponseJson response = new ResponseJson();
        if(service.isExist(categoryVO.getNewCategory())){
            response.setStatus(1);
            response.setMessage("新分类名已经存在");
        }else {
            service.updateCategory(categoryVO.getCategory(), categoryVO.getNewCategory());
            response.setStatus(0);
            response.setMessage("更新成功");
            response.setData(service.getAllCategory());
        }
        return response;
    }
}
