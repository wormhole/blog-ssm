package xyz.stackoverflow.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.util.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.service.BlogService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="/test",method=RequestMethod.GET)
    @ResponseBody
    public ResponseMessage test(){
        ResponseMessage responseMessage = new ResponseMessage();
        Blog blog = blogService.getBlogById("bc01a2b3-7ffe-4039-851f-27c50a847bb9");
        Map map = new HashMap<String,String>();
        map.put("title",blog.getTitle());
        map.put("md",blog.getBlogMd());
        map.put("html",blog.getBlogHtml());
        responseMessage.setStatus(0);
        responseMessage.setData(map);
        return responseMessage;
    }
}
