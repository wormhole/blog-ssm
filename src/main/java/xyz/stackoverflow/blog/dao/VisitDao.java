package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.VisitPO;

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

    List<VisitPO> selectByPage(Page page);

    List<VisitPO> selectByCondition(Map<String, Object> searchMap);

    VisitPO selectById(String id);

    int insert(VisitPO visit);

    int batchInsert(List<VisitPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(VisitPO visit);

    int batchUpdate(List<VisitPO> list);

    List<VisitPO> selectByDate(Date startDate, Date endDate);

}
