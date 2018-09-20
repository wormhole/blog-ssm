package xyz.stackoverflow.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.service.BlogService;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        Blog blog = blogService.getBlogById("bf69ffdd-fe23-40fc-83e7-12de15e2c50a");
        mv.setViewName("/index");
        mv.addObject("title",blog.getTitle());
        mv.addObject("md",blog.getBlogMd());
        return mv;
    }
}
