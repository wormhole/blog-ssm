package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visitor;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访客DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitorDao {

    List<Visitor> selectByPage(Page page);

    List<Visitor> selectByCondition(Map<String, Object> searchMap);

    Visitor selectById(String id);

    int insert(Visitor visitor);

    int batchInsert(List<Visitor> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Visitor visitor);

    int batchUpdate(List<Visitor> list);

    List<Visitor> selectByDate(Date startDate, Date endDate);
}
