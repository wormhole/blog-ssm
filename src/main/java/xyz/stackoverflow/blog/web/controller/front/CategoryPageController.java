package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.service.CategoryService;

/**
 * 前端页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
public class CategoryPageController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转到某一分类所有文章显示页面 /category/{categoryCode}
     * 方法 GET
     *
     * @return 返回ModelAndView,查找成功时返回分类页面,否则返回404页面
     */
    @RequestMapping(value = "/category/{categoryCode}", method = RequestMethod.GET)
    public ModelAndView category(@PathVariable("categoryCode") String categoryCode) {
        ModelAndView mv = new ModelAndView();
        if(categoryService.isExistCode(categoryCode)) {
            mv.setViewName("/category");
        }else{
            mv.setStatus(HttpStatus.NOT_FOUND);
            mv.setViewName("/error/404");
        }
        return mv;
    }
}
