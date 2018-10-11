package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

@Repository
public interface ArticleDao {
    int insertArticle(Article article);

    Article getArticleById(String id);

    Article getArticleByCode(String articleCode);

    int getArticleCount();

    int isExistCode(String articleCode);

    List<Article> getAllArticle();

    List<Article> getLimitArticle(PageParameter parameter);

    int updateArticle(Article article);

    int deleteArticleById(String id);
}
