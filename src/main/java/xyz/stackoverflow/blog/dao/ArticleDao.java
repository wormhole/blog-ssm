package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

@Repository
public interface ArticleDao {
    int insertArticle(Article article);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    int getArticleCount();

    int isExistUrl(String url);

    List<Article> getAllArticle();

    List<Article> getLimitArticle(PageParameter parameter);

    List<Article> getArticleByCategoryId(String categoryId);

    List<Article> getLimitArticleByCategoryId(PageParameter parameter);

    int updateArticle(Article article);

    int deleteArticleById(String id);
}
