package xyz.stackoverflow.blog.web.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.validator.UserValidator;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息维护页面控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/user")
public class PersonalController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private UserValidator userValidator;

    /**
     * 更新用户基本信息 /admin/user/update/baseinfo
     * 方法POST
     *
     * @param userVO 用户类VO
     * @param session 会话对象
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/update/baseinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateBaseInfo(@RequestBody UserVO userVO, HttpSession session) {
        ResponseVO response = new ResponseVO();
        User user = (User) session.getAttribute("user");
        Map<String,String> map = new HashMap<>();

        if (!userVO.getEmail().equals(user.getEmail())) {
            if (userService.isExist(userVO.getEmail())) {
                map.put("email", "邮箱已经存在");
                response.setStatus(FAILURE);
                response.setData(map);
                response.setMessage("邮箱已经存在");
                return response;
            }
        }

        map = userValidator.validate(userVO);
        if (map.size() == 0) {
            User updateUser = userVO.toUser();
            updateUser.setId(user.getId());
            if (!updateUser.getEmail().equals(user.getEmail())) {
                Cache defaultCache = redisCacheManager.getCache("defaultCache");
                defaultCache.evict("user:" + user.getEmail());
                Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
                authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
                Cache authorizationCache = redisCacheManager.getCache("authorizationCache");
                authorizationCache.evict("shiro:authorizationCache:" + user.getEmail());
            }
            User newUser = userService.updateBaseInfo(updateUser);
            session.setAttribute("user", newUser);
            UserVO vo = new UserVO();
            vo.setNickname(newUser.getNickname());
            vo.setEmail(newUser.getEmail());
            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
            response.setData(vo);
        } else {
            response.setStatus(FAILURE);
            response.setMessage("字段格式错误");
            response.setData(map);
        }
        return response;
    }

    /**
     * 更新当前用户密码 /admin/user/update/password
     * 方法POST
     *
     * @param userVO 用户类VO
     * @param session 会话对象
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updatePassword(@RequestBody UserVO userVO, HttpSession session) {
        ResponseVO response = new ResponseVO();
        User user = (User) session.getAttribute("user");
        Map<String,String> map = new HashMap<>();

        if (!user.getPassword().equals(PasswordUtil.encryptPassword(user.getSalt(), userVO.getOldPassword()))) {
            map.put("oldPassword", "旧密码不匹配");
            response.setStatus(FAILURE);
            response.setMessage("旧密码不匹配");
            response.setData(map);
            return response;
        }

        map = userValidator.validate(userVO);
        if (map.size() == 0) {
            User updateUser = userVO.toUser();
            updateUser.setId(user.getId());
            updateUser.setEmail(user.getEmail());
            Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
            authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
            Cache authorizationCache = redisCacheManager.getCache("authorizationCache");
            authorizationCache.evict("shiro:authorizationCache:" + user.getEmail());
            User newUser = userService.updatePassword(updateUser);
            session.setAttribute("user", newUser);
            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
        } else {
            response.setStatus(FAILURE);
            response.setMessage("字段格式错误");
            response.setData(map);
        }
        return response;
    }
}
