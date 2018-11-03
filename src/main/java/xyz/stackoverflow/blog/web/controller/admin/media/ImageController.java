package xyz.stackoverflow.blog.web.controller.admin.media;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 图片管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/media")
public class ImageController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    /**
     * 页面跳转 /admin/media/image
     * 方法 GET
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ModelAndView image(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String webRootDir = request.getServletContext().getRealPath("");
        String uploadDir = webRootDir + "/uploads";
        Map<String, List<String>> imageMap = new TreeMap<>();

        traverseFolder(uploadDir, imageMap);
        mv.addObject("map", imageMap);
        mv.setViewName("/admin/media/image");
        return mv;
    }

    /**
     * 图片删除接口 /admin/media/image/delete
     * 方法 POST
     *
     * @param request
     * @param url
     * @return
     */
    @RequestMapping(value = "/image/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(HttpServletRequest request, @RequestParam("url") String url) {
        ResponseVO response = new ResponseVO();
        String webRootDir = request.getServletContext().getRealPath("");
        File file = new File(webRootDir, url);
        if (file.exists()) {
            file.delete();
            response.setStatus(SUCCESS);
            response.setMessage("图片删除成功");
        } else {
            response.setStatus(FAILURE);
            response.setMessage("图片删除失败");
        }
        return response;
    }

    /**
     * 递归遍历文件夹列出图片
     *
     * @param path
     * @param imageMap
     */
    public void traverseFolder(String path, Map<String, List<String>> imageMap) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return;
            } else {
                for (File sonFile : files) {
                    if (sonFile.isDirectory()) {
                        traverseFolder(sonFile.getAbsolutePath(), imageMap);
                    } else {
                        addUrlToMap(imageMap, sonFile.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * 图片url根据规则添加进map
     *
     * @param imageMap
     * @param path
     */
    public void addUrlToMap(Map<String, List<String>> imageMap, String path) {
        String[] paths = path.split("/");
        String date = paths[paths.length - 4] + "/" + paths[paths.length - 3] + "/" + paths[paths.length - 2];
        String url = "/" + paths[paths.length - 5] + "/" + date + "/" + paths[paths.length - 1];
        if (imageMap.get(date) == null) {
            List<String> list = new ArrayList<>();
            list.add(url);
            imageMap.put(date, list);
        } else {
            List<String> list = imageMap.get(date);
            list.add(url);
            imageMap.put(date, list);
        }
    }
}
