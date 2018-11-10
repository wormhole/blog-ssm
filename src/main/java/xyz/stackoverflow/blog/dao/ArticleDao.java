package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;

/**
 * 文章表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface ArticleDao {
    int insertArticle(Article article);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    int isExistUrl(String url);

    int getArticleCount();

    int getArticleCountWithHidden();

    int getArticleCountByCategoryId(String categoryId);

    List<Article> getAllArticle();

    List<Article> getLimitArticle(PageParameter parameter);

    List<Article> getLimitArticleWithHidden(PageParameter parameter);

    List<Article> getLimitArticleByCategoryId(PageParameter parameter);

    int updateArticle(Article article);

    int deleteArticleById(String id);
}
