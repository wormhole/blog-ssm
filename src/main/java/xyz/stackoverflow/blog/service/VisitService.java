package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Visit;

import java.util.Date;

/**
 * 访问量服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitService {

    int insertVisit(Visit visit);

    int getVisitCountByDate(Date startDate, Date endDate);
}
