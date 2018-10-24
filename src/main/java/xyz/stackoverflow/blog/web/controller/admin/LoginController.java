package xyz.stackoverflow.blog.web.controller.admin;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.exception.VCodeException;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆页面控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    /**
     * Shiro登陆失败跳转方法 /admin/login
     * 方法POST
     *
     * @param request http请求对象
     * @return ModelAndView
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
        if(AuthenticationException.class.getName().equals(errorClassName)) {
            mv.addObject("error","用户名不存在");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            mv.addObject("error","密码错误");
        } else if(VCodeException.class.getName().equals((errorClassName))){
            mv.addObject("error","验证码错误");
        } else if(errorClassName != null) {
            mv.addObject("error",errorClassName);
        }
        mv.setViewName("/admin/login");
        return mv;
    }

    /**
     * 登陆页面跳转 /admin/login
     * 方法GET
     *
     * @return 返回视图名
     */
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "/admin/login";
    }
}
