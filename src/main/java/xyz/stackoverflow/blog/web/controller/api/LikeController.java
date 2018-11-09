package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.service.ArticleService;
import xyz.stackoverflow.blog.util.AbstractVO;
import xyz.stackoverflow.blog.util.BaseController;
import xyz.stackoverflow.blog.util.BaseDTO;
import xyz.stackoverflow.blog.util.Response;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 点赞接口
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class LikeController extends BaseController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;

    /**
     * 点赞接口 /api/like
     * 方法 POST
     *
     * @param dto
     * @param session
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @ResponseBody
    public Response like(@RequestBody BaseDTO dto, HttpSession session) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("article", ArticleVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("没有找到请求数据");
        }
        ArticleVO articleVO = (ArticleVO) voMap.get("article").get(0);

        Boolean isLike = (Boolean) session.getAttribute(articleVO.getUrl());
        if (isLike != null && !isLike) {
            Article article = articleService.getArticleByUrl(articleVO.getUrl());
            article.setLikes(article.getLikes() + 1);
            articleService.updateArticle(article);
            session.setAttribute(articleVO.getUrl(), true);
            response.setStatus(SUCCESS);
            response.setMessage("点赞成功");
            response.setData(article.getLikes());
        } else {
            throw new BusinessException("不能重复点赞");
        }
        return response;
    }
}
