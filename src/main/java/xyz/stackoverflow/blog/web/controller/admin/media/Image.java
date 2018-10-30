package xyz.stackoverflow.blog.web.controller.admin.media;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/media")
public class Image {

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ModelAndView image(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String webRootDir = request.getRealPath("");


    }
}
