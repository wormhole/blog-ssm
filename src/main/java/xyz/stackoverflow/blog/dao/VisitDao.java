package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitDao {

    List<Visit> selectByPage(Page page);

    List<Visit> selectByCondition(Map<String,String> searchMap);

    Visit selectById(String id);

    int insert(Visit visit);

    int batchInsert(List<Visit> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(Visit visit);

    int batchUpdate(List<Visit> list);


    int insertVisit(Visit visit);

    int getVisitCountByDate(Date startDate, Date endDate);

    int getErrorVisitCount();

    int getVisitCount();

    List<Visit> getLimitVisitByDate(Date startDate, Date endDate, Page page);

    List<Visit> getLimitErrorVisit(Page page);

    List<Visit> getLimitVisit(Page page);
}
