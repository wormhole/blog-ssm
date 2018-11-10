package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.UserVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.web.*;
import xyz.stackoverflow.blog.validator.UserValidator;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api")
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator validator;

    /**
     * 注册接口 /register
     *
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody CommonDTO dto, HttpSession session) {

        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("user", UserVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
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
            if (!MapUtil.isEmpty(map)) {
                throw new BusinessException("注册信息格式出错", map);
            }

            User user = userVO.toUser();
            user.setDeleteAble(0);
            User newUser = userService.insertUser(user);
            userService.grantRole("admin", newUser.getId());
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("注册成功");

        } else {
            throw new BusinessException("不能再进行注册");
        }

        return response;

    }

}
