package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

public interface ArticleService {
    Article insertArticle(Article blog);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    int getArticleCount();

    int getArticleCountByCategoryId(String categoryId);

    boolean isExistUrl(String url);

    List<Article> getAllArticle();

    List<Article> getLimitArticle(PageParameter parameter);

    List<Article> getArticleByCategoryId(String categoryId);

    List<Article> getLimitArticleByCategoryId(PageParameter parameter);

    Article updateArticle(Article article);

    Article deleteArticleById(String id);
}
