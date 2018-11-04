package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.VisitDao;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.Date;

/**
 * 访问量服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitDao visitDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertVisit(Visit visit) {
        visit.setId(IdGenerator.getId());
        return visitDao.insertVisit(visit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getVisitCountByDate(Date startDate, Date endDate) {
        return visitDao.getVisitCountByDate(startDate, endDate);
    }
}
