package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.VisitorDao;
import xyz.stackoverflow.blog.pojo.entity.Visitor;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访客服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visitor> selectByPage(PageParameter pageParameter) {
        return dao.selectByPage(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visitor> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visitor selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visitor insert(Visitor visitor) {
        visitor.setId(UUIDGenerator.getId());
        dao.insert(visitor);
        return dao.selectById(visitor.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Visitor> list) {
        for (Visitor visitor : list) {
            visitor.setId(UUIDGenerator.getId());
        }
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visitor deleteById(String id) {
        Visitor visitor = dao.selectById(id);
        dao.deleteById(id);
        return visitor;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visitor update(Visitor visitor) {
        dao.update(visitor);
        return dao.selectById(visitor.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Visitor> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertVisitor(Visitor visitor) {
        visitor.setId(UUIDGenerator.getId());
        return dao.insertVisitor(visitor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisitorCountByDate(Date startDate, Date endDate) {
        return dao.getVisitorCountByDate(startDate, endDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisitorCount() {
        return dao.getVisitorCount();
    }
}
