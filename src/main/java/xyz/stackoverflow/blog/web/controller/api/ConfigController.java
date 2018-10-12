package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.PersonalInfoVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.ResponseJson;

@Controller
@RequestMapping("/api")
public class ConfigController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sideinfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson user() {
        ResponseJson response = new ResponseJson();
        User admin = userService.getAdmin();
        if (admin != null) {
            PersonalInfoVO personalInfoVO = new PersonalInfoVO();
            personalInfoVO.setEmail(admin.getEmail());
            personalInfoVO.setNickname(admin.getNickname());
            personalInfoVO.setSignature(admin.getSignature());
            personalInfoVO.setHeadUrl(admin.getHeadUrl());
            response.setStatus(SUCCESS);
            response.setMessage("获取成功");
            response.setData(personalInfoVO);
        }else{
            response.setStatus(FAILURE);
            response.setMessage("获取失败");
        }
        return response;
    }

}
