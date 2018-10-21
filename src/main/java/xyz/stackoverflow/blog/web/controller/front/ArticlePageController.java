package xyz.stackoverflow.blog.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端页面跳转控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping(value = "/article", method = RequestMethod.GET)
public class ArticlePageController {

    /**
     * 跳转到文章单页 /article/{year}/{month}/{day}/{articleCode}
     * 方法 GET
     *
     * @param year
     * @param month
     * @param day
     * @param articleCode
     * @return 返回ModelAndView
     */
    @RequestMapping("/{year}/{month}/{day}/{articleCode}")
    public ModelAndView article(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day, @PathVariable("articleCode") String articleCode) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/article");
        return mv;
    }
}
