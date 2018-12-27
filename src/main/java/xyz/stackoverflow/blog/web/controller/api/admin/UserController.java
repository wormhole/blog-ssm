package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.util.ValidationUtil;
import xyz.stackoverflow.blog.util.web.*;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户数据获取接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private ValidatorFactory validatorFactory;

    /**
     * 用户信息更新接口
     * /api/admin/user/update
     * 方法 POST
     *
     * @param type
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Response updateUser(@RequestParam("type") String type, @RequestBody CommonDTO dto, HttpSession session) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("user", UserVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("未找到请求数据");
        }

        UserVO userVO = (UserVO) voMap.get("user").get(0);
        User user = (User) session.getAttribute("user");

        if (type.equals("base")) {

            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<UserVO>> violations = validator.validate(userVO, UserVO.UpdateBaseGroup.class);
            Map<String, String> map = ValidationUtil.errorMap(violations);

            if (!MapUtil.isEmpty(map)) {
                throw new BusinessException("字段格式出错", map);
            }

            if (!userVO.getEmail().equals(user.getEmail()) && userService.selectByCondition(new HashMap<String, Object>() {{
                put("email", userVO.getEmail());
            }}).size() != 0) {
                throw new BusinessException("邮箱已经存在");
            }

            User updateUser = userVO.toUser();
            updateUser.setId(user.getId());

            if (!updateUser.getEmail().equals(user.getEmail())) {
                Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
                authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
                Cache authorizationCache = redisCacheManager.getCache("authorizationCache");
                authorizationCache.evict("shiro:authorizationCache:" + user.getEmail());
            }

            User newUser = userService.update(updateUser);
            session.setAttribute("user", newUser);
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("基础信息修改成功");

        } else if (type.equals("password")) {

            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<UserVO>> violations = validator.validate(userVO, UserVO.UpdatePasswordGroup.class);
            Map<String, String> map = ValidationUtil.errorMap(violations);

            if (!MapUtil.isEmpty(map)) {
                throw new BusinessException("字段格式出错", map);
            }

            if (!user.getPassword().equals(PasswordUtil.encryptPassword(user.getSalt(), userVO.getOldPassword()))) {
                throw new BusinessException("旧密码不匹配");
            }

            User updateUser = userVO.toUser();
            updateUser.setId(user.getId());
            updateUser.setEmail(user.getEmail());
            updateUser.setSalt(PasswordUtil.getSalt());
            updateUser.setPassword(PasswordUtil.encryptPassword(updateUser.getSalt(), updateUser.getPassword()));

            Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
            authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
            Cache authorizationCache = redisCacheManager.getCache("authorizationCache");
            authorizationCache.evict("shiro:authorizationCache:" + user.getEmail());

            User newUser = userService.update(updateUser);
            session.setAttribute("user", newUser);
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("修改成功");

        } else {
            throw new BusinessException("未知请求参数type");
        }

        return response;
    }
}
