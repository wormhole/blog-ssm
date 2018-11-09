package xyz.stackoverflow.blog.web.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.*;
import xyz.stackoverflow.blog.validator.UserValidator;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人信息维护页面控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/user")
public class PersonalController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private UserValidator userValidator;

    /**
     * 更新用户信息 /admin/user/update
     * 方法 POST
     *
     * @param type
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateUser(@RequestParam("type") String type, @RequestBody BaseDTO dto, HttpSession session) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("user", UserVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("未找到请求数据");
        }
        UserVO userVO = (UserVO) voMap.get("user").get(0);
        User user = (User) session.getAttribute("user");

        Map<String, String> map = new HashMap<>();
        if (type.equals("base")) {
            if (!userVO.getEmail().equals(user.getEmail())) {
                if (userService.isExist(userVO.getEmail())) {
                    map.put("email", "邮箱已经存在");
                    throw new BusinessException("邮箱已经存在", map);
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
                User newUser = userService.updateUser(updateUser);
                session.setAttribute("user", newUser);
                UserVO vo = new UserVO();
                vo.setNickname(newUser.getNickname());
                vo.setEmail(newUser.getEmail());
                response.setStatus(SUCCESS);
                response.setMessage("修改成功");
                response.setData(vo);
            } else {
                throw new BusinessException("字段错误", map);
            }
        } else if (type.equals("password")) {
            if (!user.getPassword().equals(PasswordUtil.encryptPassword(user.getSalt(), userVO.getOldPassword()))) {
                map.put("oldPassword", "旧密码不匹配");
                throw new BusinessException("旧密码不匹配", map);
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
                User newUser = userService.updateUser(updateUser);
                session.setAttribute("user", newUser);
                response.setStatus(SUCCESS);
                response.setMessage("修改成功");
            } else {
                throw new BusinessException("字段格式错误", map);
            }
        } else {
            throw new BusinessException("类型错误");
        }
        return response;
    }
}
