package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
public class CategoryPageController {

    /**
     * 跳转到某一分类所有文章显示页面 /category/{categoryCode}
     * 方法 GET
     *
     * @return 返回ModelAndView
     */
    @RequestMapping(value = "/category/{categoryCode}", method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/category");
        return mv;
    }
}
