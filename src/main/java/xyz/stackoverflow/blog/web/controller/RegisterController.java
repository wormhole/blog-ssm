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
import xyz.stackoverflow.blog.util.ResponseStatusEnum;
import xyz.stackoverflow.blog.util.ValidateUtil;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage register(@RequestBody RegisterVO registerVO, HttpSession session) {

        ResponseMessage responseMessage = new ResponseMessage();
        String vcode = (String) session.getAttribute("vcode");

        if (!vcode.equalsIgnoreCase(registerVO.getVcode())) {
            responseMessage.setStatus(ResponseStatusEnum.VCODEERROR.getStatus());
            responseMessage.setData(ResponseStatusEnum.VCODEERROR.getMessage());
            return responseMessage;
        }

        if(userService.isExist(registerVO.getEmail())){
            responseMessage.setStatus(ResponseStatusEnum.EMAILEXISTERROR.getStatus());
            responseMessage.setData(ResponseStatusEnum.EMAILEXISTERROR.getMessage());
            return responseMessage;
        }

        Integer result = ValidateUtil.validateRegisterVO(registerVO);

        if (result.equals(ResponseStatusEnum.SUCCESS.getStatus())) {
            User user = registerVO.toUser();
            userService.addUser(user);
            responseMessage.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
            responseMessage.setData(ResponseStatusEnum.SUCCESS.getMessage());
        } else if (result.equals(ResponseStatusEnum.EMAILERROR.getStatus())) {
            responseMessage.setStatus(ResponseStatusEnum.EMAILERROR.getStatus());
            responseMessage.setData(ResponseStatusEnum.EMAILERROR.getMessage());
        } else if (result.equals(ResponseStatusEnum.NICKNAMEERROR.getStatus())) {
            responseMessage.setStatus(ResponseStatusEnum.NICKNAMEERROR.getStatus());
            responseMessage.setData(ResponseStatusEnum.NICKNAMEERROR.getMessage());
        } else if (result.equals(ResponseStatusEnum.PASSWORDERROR.getStatus())) {
            responseMessage.setStatus(ResponseStatusEnum.PASSWORDERROR.getStatus());
            responseMessage.setData(ResponseStatusEnum.PASSWORDERROR.getMessage());
        }
        return responseMessage;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/admin/register";
    }
}
