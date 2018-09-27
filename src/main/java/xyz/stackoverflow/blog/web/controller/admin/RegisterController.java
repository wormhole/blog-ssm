package xyz.stackoverflow.blog.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.util.ResponseJson;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.RegisterVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.ResponseStatusEnum;
import xyz.stackoverflow.blog.util.ValidateUtil;
import xyz.stackoverflow.blog.validator.RegisterVOValidator;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class RegisterController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson register(@RequestBody RegisterVO registerVO, HttpSession session) {

        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String, String>();

        String vcode = (String) session.getAttribute("vcode");

        if (!vcode.equalsIgnoreCase(registerVO.getVcode())) {
            map.put("vcode", "验证码错误");
            response.setStatus(FAILURE);
            response.setMessage("验证码错误");
            response.setData(map);
            return response;
        }

        if (userService.isExist(registerVO.getEmail())) {
            map.put("email", "邮箱已经存在");
            response.setStatus(FAILURE);
            response.setMessage("邮箱已经存在");
            response.setData(map);
            return response;
        }

        map = RegisterVOValidator.validate(registerVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("格式错误");
            response.setData(map);
        } else {
            response.setStatus(SUCCESS);
            response.setMessage("注册成功");
        }
        return response;

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/admin/register";
    }
}
