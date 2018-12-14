package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.pojo.entity.Visitor;
import xyz.stackoverflow.blog.util.db.PageParameter;

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

    List<Visitor> selectByPage(PageParameter pageParameter);

    List<Visitor> selectByCondition(Map<String,String> searchMap);

    Visitor selectById(String id);

    int insert(Visitor visitor);

    int batchInsert(List<Visitor> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Visitor visitor);

    int batchUpdate(List<Visitor> list);


    int insertVisitor(Visitor visitor);

    int getVisitorCountByDate(Date startDate, Date endDate);

    int getVisitorCount();
}
