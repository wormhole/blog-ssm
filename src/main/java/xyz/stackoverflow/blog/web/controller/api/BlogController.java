package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.service.BlogService;
import xyz.stackoverflow.blog.util.ResponseJson;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getAllBlog() {
        ResponseJson response = new ResponseJson();
        List<Blog> list = blogService.getAllBlog();
        response.setStatus(0);
        response.setMessage("获取成功");
        response.setData(list);
        return response;
    }

    @RequestMapping(value="/blog/{blogcode}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseJson getBlogByCode(){
        ResponseJson response = new ResponseJson();
        return response;
    }
}
