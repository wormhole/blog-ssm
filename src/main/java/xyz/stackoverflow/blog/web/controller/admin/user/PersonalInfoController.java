package xyz.stackoverflow.blog.web.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;
import xyz.stackoverflow.blog.pojo.vo.PasswordVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.util.ResponseJson;
import xyz.stackoverflow.blog.validator.BaseInfoVOValidator;
import xyz.stackoverflow.blog.validator.PasswordVOValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class PersonalInfoController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;

    @RequestMapping(value = "/update/baseinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson updateBaseInfo(@RequestBody BaseInfoVO baseInfoVO, HttpSession session) {
        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String,String>();
        User user = (User) session.getAttribute("user");

        if (!baseInfoVO.getEmail().equals(user.getEmail())) {
            if (userService.isExist(baseInfoVO.getEmail())) {
                map.put("email","邮箱已经存在");
                response.setStatus(FAILURE);
                response.setData(map);
                response.setMessage("邮箱已经存在");
                return response;
            }
        }

        map = BaseInfoVOValidator.validate(baseInfoVO);
        if(map.size()==0){
            User updateUser = baseInfoVO.toUser();
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
            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(FAILURE);
            response.setMessage("格式错误");
            response.setData(map);
        }
        return response;
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson updatePassword(@RequestBody PasswordVO passwordVO, HttpSession session) {
        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String,String>();
        User user = (User) session.getAttribute("user");

        if (!user.getPassword().equals(PasswordUtil.encryptPassword(user.getSalt(), passwordVO.getOldPassword()))) {
            map.put("oldPassword","旧密码不匹配");
            response.setStatus(FAILURE);
            response.setMessage("旧密码不匹配");
            response.setData(map);
            return response;
        }

        map = PasswordVOValidator.validatePasswordVO(passwordVO);
        if(map.size()==0){
            User updateUser = passwordVO.toUser();
            updateUser.setId(user.getId());
            updateUser.setEmail(user.getEmail());
            Cache defaultCache = redisCacheManager.getCache("defaultCache");
            defaultCache.evict("user:" + user.getEmail());
            Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
            authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
            userService.updatePassword(updateUser);
            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
        }else{
            response.setStatus(FAILURE);
            response.setMessage("格式错误");
            response.setData(map);
        }
        return response;
    }

    @RequestMapping(value = "/update/head", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson updateHead(HttpServletRequest request, HttpSession session) {
        ResponseJson response = new ResponseJson();
        Map map = new HashMap<String,String>();
        User user = (User) session.getAttribute("user");

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("headImg");
        String fileName = file.getOriginalFilename();
        String newFileName = "head" + fileName.substring(fileName.lastIndexOf("."));
        String webRootDir = request.getRealPath("");
        String homeDir = webRootDir + "/WEB-INF/uploads/" + user.getId();
        File homeFile = new File(homeDir);
        if (!homeFile.exists()) {
            homeFile.mkdirs();
        }
        File destFile = new File(homeFile,newFileName);
        try {
            file.transferTo(destFile);
            String oldUrl = user.getHeadurl();
            String newUrl = "/uploads/"+user.getId()+"/"+newFileName;
            if(!oldUrl.equals(newUrl)){
                if(!oldUrl.equals("/static/custom/image/cam.png")){
                    File oldHead = new File(webRootDir+"/WEB-INF/"+oldUrl);
                    oldHead.delete();
                }
                user.setHeadurl(newUrl);
                User newUser = userService.updateHeadUrl(user);
                session.setAttribute("user",newUser);
            }
            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
        } catch (IOException e) {
            map.put("head","头像上传失败");
            response.setStatus(FAILURE);
            response.setMessage("头像上传失败");
            response.setData(map);
        }
        return response;
    }
}
