package xyz.stackoverflow.blog.web.controller.admin.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.util.Response;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.service.CategoryService;
import xyz.stackoverflow.blog.service.CommentService;
import xyz.stackoverflow.blog.service.UserService;
import xyz.stackoverflow.blog.validator.ArticleValidator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理系统文章管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleManageController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleValidator articleValidator;

    /**
     * 获取文章 /admin/article/list
     * 方法 GET
     *
     * @param page  分页查询页码
     * @param limit 每页的条目
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Article> list = articleService.getLimitArticleWithHidden(pageParameter);


        int count = articleService.getArticleCountWithHidden();
        List<ArticleVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Article article : list) {
            ArticleVO vo = new ArticleVO();
            vo.setId(article.getId());
            vo.setTitle(HtmlUtils.htmlEscape(article.getTitle()));
            vo.setNickname(HtmlUtils.htmlEscape(userService.getUserById(article.getUserId()).getNickname()));
            vo.setCategoryName(categoryService.getCategoryById(article.getCategoryId()).getCategoryName());
            vo.setCreateDateString(sdf.format(article.getCreateDate()));
            vo.setModifyDateString(sdf.format(article.getModifyDate()));
            vo.setHits(article.getHits());
            vo.setLikes(article.getLikes());
            vo.setCommentCount(commentService.getCommentCountByArticleId(article.getId()));
            vo.setUrl(article.getUrl());
            vo.setHidden(article.getHidden());
            if (article.getHidden() == 0) {
                vo.setHiddenTag("否");
            } else {
                vo.setHiddenTag("是");
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 文章删除 /admin/article/delete
     * 方法 POST
     *
     * @param articleVO 文章VO数组
     * @return 返回ResponseVO
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody ArticleVO[] articleVO) {
        Response response = new Response();
        for (ArticleVO vo : articleVO) {
            articleService.deleteArticleById(vo.getId());
            commentService.deleteCommentByArticleId(vo.getId());
        }
        response.setStatus(SUCCESS);
        response.setMessage("删除成功");
        return response;
    }

    /**
     * 设置文章是否显示
     *
     * @param articleVO
     * @return
     */
    @RequestMapping(value = "/visitable", method = RequestMethod.POST)
    @ResponseBody
    public Response visitable(@RequestBody ArticleVO articleVO) {
        Response response = new Response();
        Article article = articleVO.toArticle();

        if (articleService.updateArticle(article) != null) {
            response.setStatus(SUCCESS);
            if (article.getHidden() == 1) {
                response.setMessage("隐藏成功");
            } else {
                response.setMessage("显示成功");
            }
        } else {
            response.setStatus(FAILURE);
            if (article.getHidden() == 1) {
                response.setMessage("隐藏失败");
            } else {
                response.setMessage("显示失败");
            }
        }
        return response;
    }

    /**
     * 导出markdown格式备份
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> export(@RequestParam("id") String id) throws IOException {
        Article article = articleService.getArticleById(id);
        String filename = article.getTitle() + ".md";
        filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");

        InputStream is = new ByteArrayInputStream(article.getArticleMd().getBytes());
        byte[] body = new byte[is.available()];
        is.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + filename);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, status);
        return entity;
    }


}
