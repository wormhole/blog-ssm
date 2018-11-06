package xyz.stackoverflow.blog.web.controller.admin.article;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.util.FileUtil;
import xyz.stackoverflow.blog.validator.ArticleValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 后台管理系统写文章控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleValidator articleValidator;

    /**
     * 保存文章 /admin/article/insert
     * 方法 POST
     *
     * @param articleVO
     * @param session
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO save(@RequestBody ArticleVO articleVO, HttpSession session) {
        ResponseVO response = new ResponseVO();

        Map<String, String> map = articleValidator.validate(articleVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段错误");
            response.setData(map);
        } else {
            if (articleService.isExistUrl(articleVO.getUrl())) {
                response.setStatus(FAILURE);
                response.setMessage("URL重复");
                map.put("url", "URL重复");
                response.setData(map);
            } else {
                User user = (User) session.getAttribute("user");
                Article article = articleVO.toArticle();
                article.setCreateDate(new Date());
                article.setModifyDate(new Date());
                article.setUserId(user.getId());
                article.setHits(0);
                article.setLikes(0);
                article.setHidden(0);
                articleService.insertArticle(article);
                response.setStatus(SUCCESS);
                response.setMessage("保存成功");
            }
        }
        return response;
    }

    /**
     * 保存图片 /admin/article/image
     * 方法 POST
     *
     * @param request
     * @param multipartFile
     * @param session
     * @return 返回Map
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Map image(HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile multipartFile) {
        JSONObject result = new JSONObject();

        String fileName = multipartFile.getOriginalFilename();
        String webRootDir = request.getServletContext().getRealPath("");
        String uploadDir = "/upload" + FileUtil.getDatePath();
        File uploadFile = new File(webRootDir + uploadDir);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File destFile = new File(uploadFile, fileName);
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            result.put("success", 0);
            result.put("message", "上传失败");
            return result.toMap();
        }
        String url = uploadDir + fileName;
        result.put("success", 1);
        result.put("message", "上传成功");
        result.put("url", url);
        return result.toMap();
    }
}
