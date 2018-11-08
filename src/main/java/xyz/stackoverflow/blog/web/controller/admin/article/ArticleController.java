package xyz.stackoverflow.blog.web.controller.admin.article;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.util.Response;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.util.DateUtil;
import xyz.stackoverflow.blog.validator.ArticleValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
    private CategoryService categoryService;
    @Autowired
    private ArticleValidator articleValidator;

    private String urlToCode(String url) {
        String[] list = url.split("/");
        return list[list.length - 1];
    }

    /**
     * 跳转到文章编辑页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView article(@RequestParam(value = "id", required = false) String id) {
        ModelAndView mv = new ModelAndView();

        List<Category> list = categoryService.getAllCategory();

        if (id != null) {
            Article article = articleService.getArticleById(id);
            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle(article.getTitle());
            articleVO.setArticleCode(urlToCode(article.getUrl()));
            articleVO.setArticleMd(article.getArticleMd());
            mv.addObject("selected", article.getCategoryId());
            mv.addObject("article", articleVO);
        } else {
            mv.addObject("selected", categoryService.getCategoryByCode("uncategory").getId());
        }

        mv.addObject("categoryList", list);
        mv.setViewName("/admin/article/article");

        return mv;
    }

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
    public Response save(@RequestBody ArticleVO articleVO, HttpSession session) {
        Response response = new Response();

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
     * 更新文章 /admin/article/update
     * 方法 POST
     *
     * @param articleVO 文章VO
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody ArticleVO articleVO) {
        Response response = new Response();
        Article article = articleService.getArticleById(articleVO.getId());

        Map<String, String> map = articleValidator.validate(articleVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段错误");
            response.setData(map);
        } else {
            String[] list = article.getUrl().split("/");
            list[list.length - 1] = articleVO.getArticleCode();
            String url = String.join("/", list);
            if (!urlToCode(article.getUrl()).equals(articleVO.getArticleCode()) && articleService.isExistUrl(url)) {
                response.setStatus(FAILURE);
                response.setMessage("URL重复");
                map.put("url", "URL重复");
                response.setData(map);
            } else {
                Article updateArticle = articleVO.toArticle();
                updateArticle.setModifyDate(new Date());
                updateArticle.setUrl(url);
                articleService.updateArticle(updateArticle);
                response.setStatus(SUCCESS);
                response.setMessage("文章更新成功");
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
     * @return 返回Map
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Map image(HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile multipartFile) {
        JSONObject result = new JSONObject();

        String fileName = multipartFile.getOriginalFilename();
        String webRootDir = request.getServletContext().getRealPath("");
        String uploadDir = "/upload" + DateUtil.getDatePath();
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
