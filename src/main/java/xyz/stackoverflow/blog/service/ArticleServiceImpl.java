package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.ArticleDao;
import xyz.stackoverflow.blog.dao.CommentDao;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 文章服务实现
 *
 * @author 凉衫薄
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> selectByPage(Page page) {
        return articleDao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> selectByCondition(Map<String, String> searchMap) {
        return articleDao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article selectById(String id) {
        return articleDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#url", unless = "#result == null")
    public Article selectByUrl(String url) {
        return articleDao.selectByUrl(url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article insert(Article article) {
        article.setId(UUIDGenerator.getId());
        articleDao.insert(article);
        return articleDao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Article> list) {
        for (Article article : list) {
            article.setId(UUIDGenerator.getId());
        }
        return articleDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null", beforeInvocation = false)
    public Article deleteById(String id) {
        Article article = articleDao.selectById(id);
        articleDao.deleteById(id);
        commentDao.deleteCommentByArticleId(id);
        return article;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return articleDao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article update(Article article) {
        articleDao.update(article);
        return articleDao.selectById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Article> list) {
        return articleDao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article insertArticle(Article article) {
        article.setId(UUIDGenerator.getId());
        articleDao.insertArticle(article);
        return articleDao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article getArticleById(String id) {
        return articleDao.getArticleById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'article:'+#url", unless = "#result == null")
    public Article getArticleByUrl(String url) {
        return articleDao.getArticleByUrl(url);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isExistUrl(String url) {
        if (articleDao.isExistUrl(url) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisibleArticleCount() {
        return articleDao.getVisibleArticleCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getArticleCount() {
        return articleDao.getArticleCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisibleArticleCountByCategoryId(String categoryId) {
        return articleDao.getVisibleArticleCountByCategoryId(categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitVisibleArticle(Page parameter) {
        return articleDao.getLimitVisibleArticle(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitArticle(Page parameter) {
        return articleDao.getLimitArticle(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Article> getLimitVisibleArticleByCategoryId(Page parameter) {
        return articleDao.getLimitVisibleArticleByCategoryId(parameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null")
    public Article updateArticle(Article article) {
        articleDao.updateArticle(article);
        return articleDao.getArticleById(article.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", allEntries = true)
    public int updateArticleCategory(String newCategoryId, String oldCategoryId) {
        return articleDao.updateArticleCategory(newCategoryId, oldCategoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "defaultCache", key = "'article:'+#result.url", condition = "#result!=null", beforeInvocation = false)
    public Article deleteArticleById(String id) {
        Article article = articleDao.getArticleById(id);
        articleDao.deleteArticleById(id);
        commentDao.deleteCommentByArticleId(id);
        return article;
    }

}
