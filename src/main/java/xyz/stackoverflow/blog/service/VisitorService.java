package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Visitor;

import java.util.Date;

/**
 * 访客服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitorService {

    int insertVisitor(Visitor visitor);

    int getVisitorCountByDate(Date startDate, Date endDate);

    int getVisitorCount();
}
