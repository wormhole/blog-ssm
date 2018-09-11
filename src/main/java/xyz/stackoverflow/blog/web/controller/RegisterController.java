package xyz.stackoverflow.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.RegisterVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.ValidateUtil;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class RegisterController {

    private final Integer success =  0;
    private final Integer emailError = 1;
    private final Integer nicknameError = 2;
    private final Integer passwordError = 3;
    private final Integer vcodeError = 4;

    private final String emailErrorInfo = "邮箱格式错误";
    private final String nicknameErrorInfo = "昵称长度要大于0";
    private final String passwordErrorInfo = "密码长度要大于等于6,且为0-9,a-z,A-Z之间";
    private final String vcodeErrorInfo = "验证码错误";

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register",method=RequestMethod.POST)
    @ResponseBody
    public ResponseMessage register(@RequestBody RegisterVO registerVO, HttpSession session){

        ResponseMessage responseMessage = new ResponseMessage();
        String vcode = (String) session.getAttribute("vcode");

        if(!vcode.equalsIgnoreCase(registerVO.getVcode())){
            responseMessage.setStatus(vcodeError);
            responseMessage.setData(vcodeErrorInfo);
            return responseMessage;
        }

        Integer result = ValidateUtil.validateRegisterInfo(registerVO);

        if(result == 0){
            User user = registerVO.toUser();
            userService.addUser(user);
            responseMessage.setStatus(success);
        }else{
            responseMessage.setStatus(result);
            if(result == 1) {
                responseMessage.setData(emailErrorInfo);
            } else if(result == 2) {
                responseMessage.setData(nicknameErrorInfo);
            } else if(result == 3) {
                responseMessage.setData(passwordErrorInfo);
            }
        }
        return responseMessage;
    }

    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String register(){
        return "/admin/register";
    }
}
