package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

public interface ArticleService {
    Article insertArticle(Article blog);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    int getArticleCount();

    boolean isExistUrl(String url);

    List<Article> getAllArticle();

    List<Article> getLimitArticle(PageParameter parameter);

    Article updateArticle(Article article);

    Article deleteArticleById(String id);
}
