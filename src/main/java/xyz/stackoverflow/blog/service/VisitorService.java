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

    List<Visitor> selectByCondition(Map<String, Object> searchMap);

    Visitor selectById(String id);

    Visitor insert(Visitor visitor);

    int batchInsert(List<Visitor> list);

    Visitor deleteById(String id);

    int batchDeleteById(List<String> list);

    Visitor update(Visitor visitor);

    int batchUpdate(List<Visitor> list);

    List<Visitor> selectByDate(Date startDate, Date endDate);
}
