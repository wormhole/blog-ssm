package xyz.stackoverflow.blog.web.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.exception.IncorrectVCodeException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
        if(AuthenticationException.class.getName().equals(errorClassName)) {
            mv.addObject("error","用户名不存在");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            mv.addObject("error","密码错误");
        } else if(IncorrectVCodeException.class.getName().equals((errorClassName))){
            mv.addObject("error","验证码错误");
        } else if(errorClassName != null) {
            mv.addObject("error",errorClassName);
        }
        mv.setViewName("/admin/login");
        return mv;
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "/admin/login";
    }
}
