package xyz.stackoverflow.blog.web.controller.api.admin;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.util.DateUtil;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.ValidationUtil;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 文章管理接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ValidatorFactory validatorFactory;

    /**
     * 通过文章url获取code
     *
     * @param url
     * @return
     */
    private String urlToCode(String url) {
        String[] list = url.split("/");
        return list[list.length - 1];
    }

    /**
     * 通过code转url
     *
     * @param code
     * @return
     */
    private String codeToUrl(String code) {
        return "/article" + DateUtil.getDatePath() + code;
    }

    /**
     * 保存文章 /api/admin/article/insert
     * 方法 POST
     *
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/article/insert", method = RequestMethod.POST)
    public Response save(@RequestBody CommonDTO dto, HttpSession session) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        ArticleVO articleVO = (ArticleVO) voMap.get("article").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ArticleVO>> violations = validator.validate(articleVO, ArticleVO.InsertGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        articleVO.setUrl(codeToUrl(articleVO.getArticleCode()));
        if (articleService.selectByUrl(articleVO.getUrl()) != null) {
            throw new BusinessException("url重复");
        }

        User user = (User) session.getAttribute("user");
        Article article = articleVO.toArticle();
        article.setCreateDate(new Date());
        article.setModifyDate(new Date());
        article.setUserId(user.getId());
        article.setHits(0);
        article.setLikes(0);
        article.setVisible(1);
        articleService.insert(article);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("保存成功");

        return response;
    }

    /**
     * 更新文章 /api/admin/article/update
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    public Response update(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        ArticleVO articleVO = (ArticleVO) voMap.get("article").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ArticleVO>> violations = validator.validate(articleVO, ArticleVO.UpdateGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Article article = articleService.selectById(articleVO.getId());

        if (article == null) {
            throw new BusinessException("未找到文章");
        }

        String[] list = article.getUrl().split("/");
        list[list.length - 1] = articleVO.getArticleCode();
        String url = String.join("/", list);

        if (!urlToCode(article.getUrl()).equals(articleVO.getArticleCode()) && (articleService.selectByUrl(url) != null)) {
            throw new BusinessException("url重复");
        }

        Article updateArticle = articleVO.toArticle();
        updateArticle.setModifyDate(new Date());
        updateArticle.setUrl(url);
        articleService.update(updateArticle);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("文章更新成功");

        return response;
    }

    /**
     * 保存图片 /api/admin/article/image
     * 方法 POST
     *
     * @param request
     * @param multipartFile
     * @return 返回Map
     */
    @RequestMapping(value = "/article/image", method = RequestMethod.POST)
    public Map image(HttpServletRequest request, @RequestParam("editormd-image-file") MultipartFile multipartFile) {
        JSONObject result = new JSONObject();

        String fileName = multipartFile.getOriginalFilename();
        String uploadDir = "/upload" + DateUtil.getDatePath();
        String destDir = request.getServletContext().getRealPath(uploadDir);

        File destDirFile = new File(destDir);
        if (!destDirFile.exists()) {
            destDirFile.mkdirs();
        }

        File destFile = new File(destDirFile, fileName);

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

    /**
     * 获取文章 /api/admin/article/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/article/list", method = RequestMethod.GET)
    public Response list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        Page page1 = new Page(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Article> list = articleService.selectByPage(page1);

        int count = articleService.selectByCondition(new HashMap<String, Object>()).size();
        List<ArticleVO> voList = new ArrayList<>();

        for (Article article : list) {
            ArticleVO vo = new ArticleVO();
            vo.setId(article.getId());
            vo.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            vo.setAuthor(HtmlUtils.htmlEscape(userService.selectById(article.getUserId()).getNickname()));
            vo.setCategoryName(categoryService.selectById(article.getCategoryId()).getCategoryName());
            vo.setCreateDate(article.getCreateDate());
            vo.setModifyDate(article.getModifyDate());
            vo.setHits(article.getHits());
            vo.setLikes(article.getLikes());
            vo.setCommentCount(commentService.selectByCondition(new HashMap<String, Object>() {{
                put("articleId", article.getId());
            }}).size());
            vo.setUrl(article.getUrl());
            if (article.getVisible() == 0) {
                vo.setVisibleTag("否");
            } else {
                vo.setVisibleTag("是");
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 文章删除 /api/admin/article/delete
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/article/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        List<SuperVO> voList = voMap.get("article");

        for (SuperVO vo : voList) {
            ArticleVO articleVO = (ArticleVO) vo;
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<ArticleVO>> violations = validator.validate(articleVO, ArticleVO.DeleteGroup.class);
            Map<String, String> map = ValidationUtil.errorMap(violations);

            if (!MapUtil.isEmpty(map)) {
                throw new BusinessException("字段格式错误", map);
            }
        }

        List<String> ids = new ArrayList<String>();
        for (SuperVO vo : voList) {
            ArticleVO articleVO = (ArticleVO) vo;
            ids.add(articleVO.getId());
        }
        articleService.batchDeleteById(ids);

        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("删除成功");
        return response;
    }

    /**
     * 设置文章是否显示 /api/admin/article/visible
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/article/visible", method = RequestMethod.POST)
    public Response visible(@RequestBody CommonDTO dto) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("找不到请求数据");
        }

        ArticleVO articleVO = (ArticleVO) voMap.get("article").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ArticleVO>> violations = validator.validate(articleVO, ArticleVO.VisibleGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Article article = articleVO.toArticle();

        if (articleService.update(article) != null) {
            response.setStatus(StatusConst.SUCCESS);
            if (article.getVisible() == 0) {
                response.setMessage("隐藏成功");
            } else {
                response.setMessage("显示成功");
            }
        } else {
            if (article.getVisible() == 0) {
                throw new BusinessException("隐藏失败");
            } else {
                throw new BusinessException("显示失败");
            }
        }
        return response;
    }

    /**
     * 导出markdown格式备份 /api/admin/article/export
     * 方法 GET
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/article/export", method = RequestMethod.GET)
    public ResponseEntity<byte[]> export(@RequestParam("id") String id) throws IOException {
        Article article = articleService.selectById(id);
        String filename = article.getTitle() + ".md";
        filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");

        InputStream is = new ByteArrayInputStream(article.getArticleMd().getBytes());
        byte[] body = new byte[is.available()];
        is.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + filename);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, status);
        return entity;
    }
}
