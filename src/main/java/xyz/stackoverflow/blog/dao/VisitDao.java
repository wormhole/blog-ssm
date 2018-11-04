package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visit;

import java.util.Date;

/**
 * 访问量DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitDao {

    int insertVisit(Visit visit);

    int getVisitCountByDate(Date startDate, Date endDate);
}
