package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitService {

    List<Visit> selectByPage(PageParameter pageParameter);

    List<Visit> selectByCondition(Map<String,String> searchMap);

    Visit selectById(String id);

    Visit insert(Visit visit);

    int batchInsert(List<Visit> list);

    Visit deleteById(String id);

    int batchDeleteById(List<String> list);

    Visit update(Visit visit);

    int batchUpdate(List<Visit> list);


    int insertVisit(Visit visit);

    int getVisitCountByDate(Date startDate, Date endDate);

    int getErrorVisitCount();

    int getVisitCount();

    List<Visit> getLimitVisitByDate(Date startDate, Date endDate, PageParameter pageParameter);

    List<Visit> getLimitErrorVisit(PageParameter pageParameter);

    List<Visit> getLimitVisit(PageParameter pageParameter);
}
