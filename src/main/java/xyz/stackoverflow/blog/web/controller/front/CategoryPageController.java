package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryPageController {

    @RequestMapping(value="/category/{categoryCode}",method=RequestMethod.GET)
    public ModelAndView category(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/category");
        return mv;
    }
}
