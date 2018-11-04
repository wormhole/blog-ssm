package xyz.stackoverflow.blog.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.pojo.vo.ArticleVO;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.service.ArticleService;

import javax.servlet.http.HttpSession;

/**
 * 点赞接口
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/api")
public class LikeController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private ArticleService articleService;

    /**
     * 点赞接口 /api/like
     * 方法 POST
     *
     * @param articleVO
     * @param session
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO like(@RequestBody ArticleVO articleVO, HttpSession session) {
        ResponseVO response = new ResponseVO();

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
            response.setStatus(FAILURE);
            response.setMessage("点赞失败");
        }
        return response;
    }
}
