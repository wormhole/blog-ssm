package xyz.stackoverflow.blog.web.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.exception.IncorrectVCodeException;
import xyz.stackoverflow.blog.pojo.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {

    private final Integer vcodeError = 1;
    private final Integer emailError = 2;
    private final Integer passwordError = 3;
    private final Integer otherError = 4;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(HttpServletRequest request){

        ResponseMessage response = new ResponseMessage();

        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
        if(AuthenticationException.class.getName().equals(errorClassName)) {
            response.setStatus(emailError);
            response.setData(new String("用户不存在"));
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            response.setStatus(passwordError);
            response.setData(new String("密码错误"));
        } else if(IncorrectVCodeException.class.getName().equals((errorClassName))){
            response.setStatus(vcodeError);
            response.setData(new String("验证码错误"));
        } else if(errorClassName != null) {
            response.setStatus(otherError);
            response.setData(new String("其他错误:" + errorClassName));
        }

        return response;
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "/user/login";
    }
}
