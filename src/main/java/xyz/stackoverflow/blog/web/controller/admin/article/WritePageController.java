package xyz.stackoverflow.blog.web.controller.admin.article;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.stackoverflow.blog.util.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.BlogVO;
import xyz.stackoverflow.blog.service.BlogService;
import xyz.stackoverflow.blog.util.FileUtil;
import xyz.stackoverflow.blog.util.ResponseStatusEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class WritePageController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save(@RequestBody BlogVO blogVO,HttpSession session){
        ResponseMessage response = new ResponseMessage();
        Blog blog = blogVO.toBlog();
        blog.setUserId(((User)session.getAttribute("user")).getId());
        blogService.saveBlog(blog);
        response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
        response.setData(ResponseStatusEnum.SUCCESS.getMessage());
        return response;
    }

    @RequestMapping(value="/image",method=RequestMethod.POST)
    @ResponseBody
    public Map image(HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile multipartFile, HttpSession session){
        JSONObject result = new JSONObject();
        User user = (User) session.getAttribute("user");
        String webRootDir = request.getRealPath("");
        String homeDir = webRootDir + "/WEB-INF/uploads/" + user.getId();
        String dateDir = FileUtil.getDatePath();
        String uploadDir = homeDir+dateDir;
        File uploadDirFile = new File(uploadDir);
        if(!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        File destFile = new File(uploadDirFile,fileName);
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            result.put("success",0);
            result.put("message","上传失败");
            return result.toMap();
        }
        String url = "/uploads/"+user.getId()+dateDir+fileName;
        result.put("success",1);
        result.put("message","上传成功");
        result.put("url",url);
        return result.toMap();
    }
}
