package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Article;

import java.util.List;

public interface ArticleService {
    Article insertArticle(Article blog);

    Article getArticleById(String id);

    Article getArticleByCode(String articleCode);

    boolean isExistCode(String articleCode);

    List<Article> getAllArticle();

    Article updateArticle(Article article);

    Article deleteArticleById(String id);
}
