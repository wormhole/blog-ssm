package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Visitor;

import java.util.Date;

/**
 * 访客DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitorDao {

    int insertVisitor(Visitor visitor);

    int getVisitorCountByDate(Date startDate, Date endDate);

    int getVisitorCount();
}
