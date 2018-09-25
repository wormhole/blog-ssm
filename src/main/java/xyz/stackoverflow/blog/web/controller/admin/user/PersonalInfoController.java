package xyz.stackoverflow.blog.web.controller.admin.user;

import com.hazelcast.spi.exception.ResponseNotSentException;
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
import xyz.stackoverflow.blog.util.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;
import xyz.stackoverflow.blog.pojo.vo.PasswordVO;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.PasswordUtil;
import xyz.stackoverflow.blog.util.ResponseStatusEnum;
import xyz.stackoverflow.blog.util.ValidateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/user")
public class PersonalInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheManager redisCacheManager;

    @RequestMapping(value = "/update/baseinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateBaseInfo(@RequestBody BaseInfoVO baseInfoVO, HttpSession session) {
        ResponseMessage response = new ResponseMessage();
        User user = (User) session.getAttribute("user");

        if (!baseInfoVO.getEmail().equals(user.getEmail())) {
            if (userService.isExist(baseInfoVO.getEmail())) {
                response.setStatus(ResponseStatusEnum.EMAILEXISTERROR.getStatus());
                response.setData(ResponseStatusEnum.EMAILEXISTERROR.getMessage());
                return response;
            }
        }

        Integer result = ValidateUtil.validateBaseInfoVO(baseInfoVO);
        if (result.equals(ResponseStatusEnum.SUCCESS.getStatus())) {
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
            response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
        } else if (result.equals(ResponseStatusEnum.EMAILERROR.getStatus())) {
            response.setStatus(ResponseStatusEnum.EMAILERROR.getStatus());
            response.setData(ResponseStatusEnum.EMAILERROR.getMessage());
        } else if (result.equals(ResponseStatusEnum.NICKNAMEERROR.getStatus())) {
            response.setStatus(ResponseStatusEnum.NICKNAMEERROR.getStatus());
            response.setData(ResponseStatusEnum.NICKNAMEERROR.getMessage());
        } else if(result.equals(ResponseStatusEnum.SIGNATRUEERROR.getStatus())){
            response.setStatus(ResponseStatusEnum.SIGNATRUEERROR.getStatus());
            response.setData(ResponseStatusEnum.SIGNATRUEERROR.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updatePassword(@RequestBody PasswordVO passwordVO, HttpSession session) {
        ResponseMessage response = new ResponseMessage();
        User user = (User) session.getAttribute("user");

        if (!user.getPassword().equals(PasswordUtil.encryptPassword(user.getSalt(), passwordVO.getOldPassword()))) {
            response.setStatus(ResponseStatusEnum.OLDPASSWORDERROR.getStatus());
            response.setData(ResponseStatusEnum.OLDPASSWORDERROR.getMessage());
            return response;
        }

        Integer result = ValidateUtil.validatePasswordVO(passwordVO);
        if (result.equals(ResponseStatusEnum.SUCCESS.getStatus())) {
            User updateUser = passwordVO.toUser();
            updateUser.setId(user.getId());
            updateUser.setEmail(user.getEmail());
            Cache defaultCache = redisCacheManager.getCache("defaultCache");
            defaultCache.evict("user:" + user.getEmail());
            Cache authenticationCache = redisCacheManager.getCache("authenticationCache");
            authenticationCache.evict("shiro:authenticationCache:" + user.getEmail());
            userService.updatePassword(updateUser);
            response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
            response.setData(ResponseStatusEnum.SUCCESS.getMessage());
        } else if (result.equals(ResponseStatusEnum.PASSWORDERROR.getStatus())) {
            response.setStatus(ResponseStatusEnum.PASSWORDERROR.getStatus());
            response.setData(ResponseStatusEnum.PASSWORDERROR.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update/head", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateHead(HttpServletRequest request, HttpSession session) {
        ResponseMessage response = new ResponseMessage();
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
            response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
            response.setData(ResponseStatusEnum.SUCCESS.getMessage());
        } catch (IOException e) {
            response.setStatus(ResponseStatusEnum.HEADERROR.getStatus());
            response.setData(ResponseStatusEnum.HEADERROR.getMessage());
        }
        return response;
    }
}
