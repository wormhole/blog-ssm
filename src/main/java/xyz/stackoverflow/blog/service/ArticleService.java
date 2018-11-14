package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;

/**
 * 文章服务接口
 *
 * @author 凉衫薄
 */
public interface ArticleService {
    Article insertArticle(Article blog);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    boolean isExistUrl(String url);

    int getVisibleArticleCount();

    int getArticleCount();

    int getVisibleArticleCountByCategoryId(String categoryId);

    List<Article> getLimitVisibleArticle(PageParameter parameter);

    List<Article> getLimitArticle(PageParameter pageParameter);

    List<Article> getLimitVisibleArticleByCategoryId(PageParameter parameter);

    Article updateArticle(Article article);

    int updateArticleCategory(String newCategoryId, String oldCategoryId);

    Article deleteArticleById(String id);
}
