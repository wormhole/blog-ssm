package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.VisitorPO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访客DAO类
 *
 * @author 凉衫薄
 */
@Repository
public interface VisitorDao {

    List<VisitorPO> selectByPage(Page page);

    List<VisitorPO> selectByCondition(Map<String, Object> searchMap);

    VisitorPO selectById(String id);

    int insert(VisitorPO visitor);

    int batchInsert(List<VisitorPO> list);

    int deleteById(String id);

    int batchDeleteById(List<String> list);

    int update(VisitorPO visitor);

    int batchUpdate(List<VisitorPO> list);

    List<VisitorPO> selectByDate(Date startDate, Date endDate);
}
