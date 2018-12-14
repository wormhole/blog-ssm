package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Visitor;
import xyz.stackoverflow.blog.util.db.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访客服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitorService {

    List<Visitor> selectByPage(Page page);

    List<Visitor> selectByCondition(Map<String,String> searchMap);

    Visitor selectById(String id);

    Visitor insert(Visitor visitor);

    int batchInsert(List<Visitor> list);

    Visitor deleteById(String id);

    int batchDeleteById(List<String> list);

    Visitor update(Visitor visitor);

    int batchUpdate(List<Visitor> list);


    int insertVisitor(Visitor visitor);

    int getVisitorCountByDate(Date startDate, Date endDate);

    int getVisitorCount();
}
