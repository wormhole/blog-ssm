package xyz.stackoverflow.blog.web.controller.admin.article;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.ResponseJson;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.util.FileUtil;
import xyz.stackoverflow.blog.validator.ArticleValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class WriteArticleController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleValidator articleValidator;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson save(@RequestBody ArticleVO articleVO, HttpSession session) {
        ResponseJson response = new ResponseJson();

        Map map = articleValidator.validate(articleVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段错误");
            response.setData(map);
        } else {
            Map map1 = new HashMap<String, String>();
            if (articleService.isExistCode(articleVO.getArticleCode())) {
                response.setStatus(FAILURE);
                response.setMessage("编码重复");
                map1.put("code", "编码重复");
                response.setData(map1);
            } else {
                User user = (User) session.getAttribute("user");
                Article article = articleVO.toArticle();
                article.setUserId(user.getId());
                articleService.insertArticle(article);
                response.setStatus(SUCCESS);
                response.setMessage("保存成功");
            }
        }
        return response;
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Map image(HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile multipartFile, HttpSession session) {
        JSONObject result = new JSONObject();
        User user = (User) session.getAttribute("user");
        String webRootDir = request.getRealPath("");
        String homeDir = webRootDir + "/WEB-INF/uploads/" + user.getId();
        String dateDir = FileUtil.getDatePath();
        String uploadDir = homeDir + dateDir;
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        File destFile = new File(uploadDirFile, fileName);
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            result.put("success", 0);
            result.put("message", "上传失败");
            return result.toMap();
        }
        String url = "/uploads/" + user.getId() + dateDir + fileName;
        result.put("success", 1);
        result.put("message", "上传成功");
        result.put("url", url);
        return result.toMap();
    }
}
