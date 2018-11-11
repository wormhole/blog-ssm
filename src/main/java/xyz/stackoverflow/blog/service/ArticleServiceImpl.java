package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.ArticleDao;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;

/**
 * 文章服务实现
 *
 * @author 凉衫薄
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article insertArticle(Article article) {
        article.setId(UUIDGenerator.getId());
        dao.insertArticle(article);
        return dao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article getArticleById(String id) {
        return dao.getArticleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#url", unless = "#result == null")
    public Article getArticleByUrl(String url) {
        return dao.getArticleByUrl(url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistUrl(String url) {
        if (dao.isExistUrl(url) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisibleArticleCount() {
        return dao.getVisibleArticleCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getArticleCount() {
        return dao.getArticleCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisibleArticleCountByCategoryId(String categoryId) {
        return dao.getVisibleArticleCountByCategoryId(categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getAllArticle() {
        return dao.getAllArticle();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitVisibleArticle(PageParameter parameter) {
        return dao.getLimitVisibleArticle(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitArticle(PageParameter parameter) {
        return dao.getLimitArticle(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitVisibleArticleByCategoryId(PageParameter parameter) {
        return dao.getLimitVisibleArticleByCategoryId(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article updateArticle(Article article) {
        dao.updateArticle(article);
        return dao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null", beforeInvocation = false)
    public Article deleteArticleById(String id) {
        Article article = dao.getArticleById(id);
        dao.deleteArticleById(id);
        return article;
    }

}
