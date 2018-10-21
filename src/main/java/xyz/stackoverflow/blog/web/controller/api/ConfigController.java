package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;

/**
 * 配置获取控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class ConfigController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;

    /**
     * 获取侧边栏信息 /api/sideinfo
     * 方法 GET
     *
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/sideinfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO user() {
        ResponseVO response = new ResponseVO();
        User admin = userService.getAdmin();
        if (admin != null) {
            UserVO userVO = new UserVO();
            userVO.setEmail(admin.getEmail());
            userVO.setNickname(admin.getNickname());
            userVO.setSignature(admin.getSignature());
            userVO.setHeadUrl(admin.getHeadUrl());
            response.setStatus(SUCCESS);
            response.setMessage("获取成功");
            response.setData(userVO);
        }else{
            response.setStatus(FAILURE);
            response.setMessage("获取失败");
        }
        return response;
    }

}
