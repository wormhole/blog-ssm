package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.VisitDao;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.util.db.Page;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visit> selectByPage(Page page) {
        return dao.selectByPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visit> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visit selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visit insert(Visit visit) {
        visit.setId(UUIDGenerator.getId());
        dao.insert(visit);
        return dao.selectById(visit.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Visit> list) {
        for (Visit visit : list) {
            visit.setId(UUIDGenerator.getId());
        }
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visit deleteById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Visit update(Visit visit) {
        dao.update(visit);
        return dao.selectById(visit.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Visit> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertVisit(Visit visit) {
        visit.setId(UUIDGenerator.getId());
        return dao.insertVisit(visit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisitCountByDate(Date startDate, Date endDate) {
        return dao.getVisitCountByDate(startDate, endDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getErrorVisitCount() {
        return dao.getErrorVisitCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisitCount() {
        return dao.getVisitCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visit> getLimitVisitByDate(Date startDate, Date endDate, Page page) {
        return dao.getLimitVisitByDate(startDate, endDate, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visit> getLimitErrorVisit(Page page) {
        return dao.getLimitErrorVisit(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Visit> getLimitVisit(Page page) {
        return dao.getLimitVisit(page);
    }
}
