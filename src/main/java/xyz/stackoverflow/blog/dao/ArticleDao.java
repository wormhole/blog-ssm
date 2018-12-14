package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.List;
import java.util.Map;

/**
 * 文章表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface ArticleDao {

    List<Article> selectByPage(Page page);

    List<Article> selectByCondition(Map<String, String> searchMap);

    Article selectById(String id);

    Article selectByUrl(String url);

    int insert(Article article);

    int batchInsert(List<Article> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Article article);

    int batchUpdate(List<Article> list);


    int insertArticle(Article article);

    Article getArticleById(String id);

    Article getArticleByUrl(String url);

    int isExistUrl(String url);

    int getVisibleArticleCount();

    int getArticleCount();

    int getVisibleArticleCountByCategoryId(String categoryId);

    List<Article> getLimitVisibleArticle(Page parameter);

    List<Article> getLimitArticle(Page parameter);

    List<Article> getLimitVisibleArticleByCategoryId(Page parameter);

    int updateArticle(Article article);

    int deleteArticleById(String id);

    int updateArticleCategory(String newCategoryId, String oldCategoryId);
}
