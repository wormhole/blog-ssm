package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.ArticleDao;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.IdGenerator;
import xyz.stackoverflow.blog.util.PageParameter;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.articleCode")
    public Article insertArticle(Article blog) {
        blog.setId(IdGenerator.getId());
        blog.setTitle(blog.getTitle());
        dao.insertArticle(blog);
        return dao.getArticleById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article getArticleById(String id) {
        return dao.getArticleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#articleCode", unless = "#ressult == null")
    public Article getArticleByCode(String articleCode) {
        return dao.getArticleByCode(articleCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getArticleCount() {
        return dao.getArticleCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getAllArticle() {
        return dao.getAllArticle();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitArticle(PageParameter parameter) {
        return dao.getLimitArticle(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistCode(String articleCode) {
        if (dao.isExistCode(articleCode) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.articleCode")
    public Article updateArticle(Article article) {
        dao.updateArticle(article);
        return dao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'article:'+#result.articleCode")
    public Article deleteArticleById(String id) {
        Article article = dao.getArticleById(id);
        dao.deleteArticleById(id);
        return article;
    }

}
