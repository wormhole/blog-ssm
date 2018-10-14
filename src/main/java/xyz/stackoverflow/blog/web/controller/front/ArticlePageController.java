package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticlePageController {

    @RequestMapping("/{year}/{month}/{day}/{articleCode}")
    public ModelAndView article(@PathVariable("articleCode") String articleCode){
        ModelAndView mv = new ModelAndView();
        mv.addObject("articleCode",articleCode);
        mv.setViewName("/article");
        return mv;
    }
}
