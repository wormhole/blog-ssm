package xyz.stackoverflow.blog.web.controller.admin.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.pojo.vo.SettingVO;
import xyz.stackoverflow.blog.service.SettingService;
import xyz.stackoverflow.blog.util.*;
import xyz.stackoverflow.blog.validator.SettingValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客设置控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private SettingService settingService;
    @Autowired
    private SettingValidator settingValidator;

    /**
     * 更改基础设置
     *
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody BaseDTO dto, HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();
        ServletContext application = request.getServletContext();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("setting", SettingVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("未找到请求数据");
        }
        List<AbstractVO> voList = voMap.get("setting");
        SettingVO[] vos = voList.toArray(new SettingVO[0]);

        Map<String, String> map = settingValidator.validate(vos);

        if (map.size() == 0) {
            for (SettingVO settingVO : vos) {
                Setting setting = settingVO.toSetting();
                settingService.updateSetting(setting);
            }

            List<Setting> list = settingService.getAllSetting();
            Map<String, Object> settingMap = new HashMap<>();
            for (Setting setting : list) {
                settingMap.put(setting.getKey(), setting.getValue());
            }
            application.setAttribute("setting", settingMap);

            response.setStatus(SUCCESS);
            response.setMessage("配置更改成功");
        } else {
            throw new BusinessException("字段格式错误", map);
        }
        return response;
    }

    /**
     * 更改头像
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/head", method = RequestMethod.POST)
    @ResponseBody
    public Response updateHead(HttpServletRequest request) {
        Response response = new Response();
        ServletContext application = request.getServletContext();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("headImg");
        String fileName = file.getOriginalFilename();
        String webRootDir = request.getServletContext().getRealPath("");
        String uploadDir = "/upload" + DateUtil.getDatePath();
        File uploadFile = new File(webRootDir + uploadDir);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File destFile = new File(uploadFile, fileName);
        try {
            file.transferTo(destFile);
            String url = uploadDir + fileName;
            Setting setting = new Setting();
            setting.setKey("head");
            setting.setValue(url);
            settingService.updateSetting(setting);

            Map<String, Object> settingMap = (Map<String, Object>) application.getAttribute("setting");
            settingMap.replace("head", url);
            application.setAttribute("setting", settingMap);

            response.setStatus(SUCCESS);
            response.setMessage("修改成功");
            response.setData(setting);
        } catch (IOException e) {
            throw new BusinessException("头像上传失败");
        }
        return response;
    }
}
