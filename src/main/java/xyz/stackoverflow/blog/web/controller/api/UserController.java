package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;

/**
 * 获取管理员信息控制器
 * 
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class UserController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;

    /**
     * 获取管理员信息 /api/user/admin
     * 方法 GET
     *
     * @return
     */
    @RequestMapping(value="/user/admin",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO admin(){
        ResponseVO response = new ResponseVO();
        User user = userService.getAdmin();
        UserVO vo = new UserVO();
        vo.setHeadUrl(user.getHeadUrl());
        vo.setNickname(user.getNickname());
        vo.setSignature(HtmlUtils.htmlEscape(user.getSignature()));
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(vo);
        return response;
    }
}
