package xyz.stackoverflow.blog.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.AbstractVO;
import xyz.stackoverflow.blog.util.BaseController;
import xyz.stackoverflow.blog.util.BaseDTO;
import xyz.stackoverflow.blog.util.Response;
import xyz.stackoverflow.blog.validator.UserValidator;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册页面控制器
 *
 * @author 凉衫薄
 */
@Controller
public class RegisterPageController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;
    private final Map<String, Class<? extends AbstractVO>> clazzMap = new HashMap<String, Class<? extends AbstractVO>>() {{
        put("user", UserVO.class);
    }};

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody BaseDTO dto, HttpSession session) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Response response = new Response();

        Map<String, List<AbstractVO>> voMap = dto2vo(clazzMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("没有找到请求数据");
        }
        UserVO userVO = (UserVO) voMap.get("user").get(0);

        User admin = userService.getAdmin();
        if (admin == null) {
            Map<String, String> map = new HashMap<>();

            String vcode = (String) session.getAttribute("vcode");
            if (!vcode.equalsIgnoreCase(userVO.getVcode())) {
                map.put("vcode", "验证码错误");
                throw new BusinessException("验证码错误", map);
            }

            if (userService.isExist(userVO.getEmail())) {
                map.put("email", "邮箱已经存在");
                throw new BusinessException("邮箱已经存在", map);
            }

            map = validator.validate(userVO);
            if (map.size() != 0) {
                throw new BusinessException("注册信息格式出错", map);
            } else {
                User user = userVO.toUser();
                user.setDeleteAble(0);
                User newUser = userService.insertUser(user);
                userService.grantRole("admin", newUser.getId());
                response.setStatus(SUCCESS);
                response.setMessage("注册成功");
            }
        } else {
            throw new BusinessException("不能再进行注册");
        }
        return response;

    }

    /**
     * 注册页面跳转 /register
     * 方法 GET
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/register";
    }
}
