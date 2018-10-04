package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.dao.ArticleDao;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.id")
    public Article insertArticle(Article blog) {
        blog.setId(IdGenerator.getId());
        blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
        dao.insertArticle(blog);
        return dao.getArticleById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#id", unless = "#result == null")
    public Article getArticleById(String id) {
        return dao.getArticleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#blogCode", unless = "#ressult == null")
    public Article getArticleByCode(String blogCode) {
        return dao.getArticleByCode(blogCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getAllArticle() {
        return dao.getAllArticle();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistCode(String blogCode) {
        if (dao.isExistCode(blogCode) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.id")
    public Article updateArticle(Article blog) {
        dao.updateArticle(blog);
        return dao.getArticleById(blog.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'blog:'+#id")
    public Article deleteArticleById(String id) {
        Article blog = dao.getArticleById(id);
        dao.deleteArticleById(id);
        return blog;
    }

}
