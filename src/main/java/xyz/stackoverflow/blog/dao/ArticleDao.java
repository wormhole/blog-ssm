package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;

import java.util.List;

@Repository
public interface ArticleDao {
    int insertArticle(Article blog);

    Article getArticleById(String id);

    Article getArticleByCode(String articleCode);

    int isExistCode(String articleCode);

    List<Article> getAllArticle();

    Article updateArticle(Article article);

    Article deleteArticleById(String id);
}
