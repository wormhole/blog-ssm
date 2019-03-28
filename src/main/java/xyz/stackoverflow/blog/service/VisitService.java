package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.VisitPO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量服务类接口
 *
 * @author 凉衫薄
 */
public interface VisitService {

    List<VisitPO> selectByPage(Page page);

    List<VisitPO> selectByCondition(Map<String, Object> searchMap);

    VisitPO selectById(String id);

    VisitPO insert(VisitPO visit);

    int batchInsert(List<VisitPO> list);

    VisitPO deleteById(String id);

    int batchDeleteById(List<String> list);

    VisitPO update(VisitPO visit);

    int batchUpdate(List<VisitPO> list);

    List<VisitPO> selectByDate(Date startDate, Date endDate);

}
