package xyz.stackoverflow.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.ValidateUtil;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Integer success = 0;
    private final Integer emailError = 1;
    private final Integer nicknameError = 2;
    private final Integer emailExistError = 3;

    private final String emailErrorInfo = "邮箱格式错误";
    private final String nicknameErrorInfo = "昵称长度要大于0";
    private final String emailExistErrorInfo = "邮箱已经存在";

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;

    @RequestMapping(value = "/update/baseinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage update(@RequestBody BaseInfoVO baseInfoVO, HttpSession session) {
        ResponseMessage response = new ResponseMessage();
        User user = (User) session.getAttribute("user");

        if (!baseInfoVO.getEmail().equals(user.getEmail())) {
            if (userService.isExist(baseInfoVO.getEmail())) {
                response.setStatus(emailExistError);
                response.setData(emailExistErrorInfo);
                return response;
            }
        }

        Integer result = ValidateUtil.validateBaseInfo(baseInfoVO);
        if (result == 0) {
            User updateUser = baseInfoVO.toUser();
            updateUser.setId(user.getId());
            if(!updateUser.getEmail().equals(user.getEmail())) {
                Cache defaultCache = redisCacheManager.getCache("defaultCache");
                defaultCache.evict("user:" + user.getEmail());
                Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
                authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
                Cache authorizationCache = redisCacheManager.getCache("authorizationCache");
                authorizationCache.evict("shiro:authorizationCache:" + user.getEmail());
            }
            User newUser = userService.updateEmailAndNickname(updateUser);
            session.setAttribute("user", newUser);
            response.setStatus(success);
        } else if (result == 1) {
            response.setStatus(emailError);
            response.setData(emailErrorInfo);
        } else if (result == 2) {
            response.setStatus(nicknameError);
            response.setData(nicknameErrorInfo);
        }
        return response;
    }
}
