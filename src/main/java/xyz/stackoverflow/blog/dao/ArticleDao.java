package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.ArticlePO;

import java.util.List;
import java.util.Map;

/**
 * 文章表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface ArticleDao {

    List<ArticlePO> selectByPage(Page page);

    List<ArticlePO> selectByCondition(Map<String, Object> searchMap);

    ArticlePO selectById(String id);

    ArticlePO selectByUrl(String url);

    int insert(ArticlePO article);

    int batchInsert(List<ArticlePO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(ArticlePO article);

    int batchUpdate(List<ArticlePO> list);

}
