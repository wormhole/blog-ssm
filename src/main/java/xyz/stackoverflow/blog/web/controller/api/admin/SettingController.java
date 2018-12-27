package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.pojo.vo.SettingVO;
import xyz.stackoverflow.blog.service.SettingService;
import xyz.stackoverflow.blog.util.DateUtil;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.web.*;
import xyz.stackoverflow.blog.validator.SettingValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置信息更新接口
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class SettingController extends BaseController {

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
    @RequestMapping(value = "/setting/update", method = RequestMethod.POST)
    public Response update(@RequestBody CommonDTO dto, HttpServletRequest request) {
        Response response = new Response();
        ServletContext application = request.getServletContext();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("setting", SettingVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("未找到请求数据");
        }

        List<SuperVO> voList = voMap.get("setting");
        SettingVO[] vos = voList.toArray(new SettingVO[0]);

        Map<String, String> map = settingValidator.validate(vos);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        for (SettingVO settingVO : vos) {
            Setting setting = settingVO.toSetting();
            settingService.update(setting);
        }

        List<Setting> settingList = settingService.selectByCondition(new HashMap<String, Object>());
        Map<String, Object> settingMap = new HashMap<>();
        for (Setting setting : settingList) {
            settingMap.put(setting.getName(), setting.getValue());
        }
        application.setAttribute("setting", settingMap);

        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("配置更改成功");

        return response;
    }

    /**
     * 更改头像
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/setting/update/head", method = RequestMethod.POST)
    @ResponseBody
    public Response updateHead(HttpServletRequest request) {
        Response response = new Response();
        ServletContext application = request.getServletContext();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("headImg");
        String fileName = file.getOriginalFilename();
        String uploadDir = "/upload" + DateUtil.getDatePath();
        String destDir = request.getServletContext().getRealPath(uploadDir);

        File destDirFile = new File(destDir);
        if (!destDirFile.exists()) {
            destDirFile.mkdirs();
        }

        File destFile = new File(destDirFile, fileName);
        try {
            file.transferTo(destFile);
            String url = uploadDir + fileName;
            Setting setting = new Setting();
            setting.setName("head");
            setting.setValue(url);
            settingService.update(setting);

            Map<String, Object> settingMap = (Map<String, Object>) application.getAttribute("setting");
            settingMap.replace("head", url);
            application.setAttribute("setting", settingMap);

            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("修改成功");
            response.setData(setting);
        } catch (IOException e) {
            throw new BusinessException("头像上传失败");
        }
        return response;
    }
}
