package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.Date;
import java.util.List;

/**
 * 访问量DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitDao {

    int insertVisit(Visit visit);

    int getVisitCountByDate(Date startDate, Date endDate);

    int getErrorVisitCount();

    int getVisitCount();

    List<Visit> getLimitVisitByDate(Date startDate, Date endDate, PageParameter pageParameter);

    List<Visit> getLimitErrorVisit(PageParameter pageParameter);
}
