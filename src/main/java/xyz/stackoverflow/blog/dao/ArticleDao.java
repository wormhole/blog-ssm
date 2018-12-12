package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Article;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 文章表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface ArticleDao {

    List<Article> selectByPage(PageParameter pageParameter);

    List<Article> selectByCondition(Map<String, String> searchMap);

    Article selectById(String id);

    Article selectByUrl(String url);

    int insert(Article article);

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

    List<Article> getLimitVisibleArticle(PageParameter parameter);

    List<Article> getLimitArticle(PageParameter parameter);

    List<Article> getLimitVisibleArticleByCategoryId(PageParameter parameter);

    int updateArticle(Article article);

    int deleteArticleById(String id);

    int updateArticleCategory(String newCategoryId, String oldCategoryId);
}
