package xyz.stackoverflow.blog.web.controller.admin.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.pojo.vo.SettingVO;
import xyz.stackoverflow.blog.service.SettingService;
import xyz.stackoverflow.blog.util.FileUtil;
import xyz.stackoverflow.blog.validator.SettingValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
public class SettingController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private SettingService settingService;
    @Autowired
    private SettingValidator settingValidator;

    /**
     * 更改基础设置
     *
     * @param settingVOS
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO update(@RequestBody SettingVO[] settingVOS, HttpServletRequest request) {
        ResponseVO response = new ResponseVO();
        ServletContext application = request.getServletContext();

        Map<String, String> map = settingValidator.validate(settingVOS);

        if (map.size() == 0) {
            for (SettingVO settingVO : settingVOS) {
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
            response.setStatus(FAILURE);
            response.setMessage("字段验证出错");
            response.setData(map);
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
    public ResponseVO updateHead(HttpServletRequest request) {
        ResponseVO response = new ResponseVO();
        ServletContext application = request.getServletContext();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("headImg");
        String fileName = file.getOriginalFilename();
        String webRootDir = request.getServletContext().getRealPath("");
        String uploadDir = "/upload" + FileUtil.getDatePath();
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
            response.setStatus(FAILURE);
            response.setMessage("头像上传失败");
        }
        return response;
    }
}
