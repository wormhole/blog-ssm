package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao {
    int insertArticle(Article article);

    Article getArticleById(String id);

    Article getArticleByCode(String articleCode);

    int isExistCode(String articleCode);

    List<Article> getAllArticle();

    int updateArticle(Article article);

    int updateArticleCategory(Map map);

    int deleteArticleById(String id);
}
