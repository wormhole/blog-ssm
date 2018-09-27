package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.vo.CategoryVO;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.ResponseMessage;

@Controller
@RequestMapping("/admin/article")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping("/category/insert")
    @ResponseBody
    public ResponseMessage insert(@RequestBody CategoryVO categoryVO){
        ResponseMessage respnose = new ResponseMessage();
        Category category = categoryVO.toCategory();
        service.insertCategory(category);
        return respnose;
    }
}
