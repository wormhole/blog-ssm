package xyz.stackoverflow.blog.web.controller.admin.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.util.web.BaseController;

/**
 * 博客设置页面跳转Controller
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingPageController extends BaseController {

    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public ModelAndView setting() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/setting/setting");
        return mv;
    }
}
