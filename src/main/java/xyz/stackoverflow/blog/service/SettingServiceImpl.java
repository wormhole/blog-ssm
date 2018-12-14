package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.SettingDao;
import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.db.UUIDGenerator;

import java.util.List;
import java.util.Map;

/**
 * 设置服务实现类
 *
 * @author 凉衫薄
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Setting> selectByPage(PageParameter pageParameter) {
        return dao.selectByPage(pageParameter);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Setting> selectByCondition(Map<String, String> searchMap) {
        return dao.selectByCondition(searchMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Setting selectById(String id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'setting:'+#result.key", condition = "#result != null")
    public Setting insert(Setting setting) {
        setting.setId(UUIDGenerator.getId());
        dao.insert(setting);
        return dao.selectById(setting.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Setting> list) {
        return dao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Setting deleteById(String id) {
        Setting setting = dao.selectById(id);
        dao.deleteById(id);
        return setting;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteById(List<String> list) {
        return dao.batchDeleteById(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'setting:'+#result.key", condition = "#result != null")
    public Setting update(Setting setting) {
        dao.update(setting);
        return dao.selectById(setting.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Setting> list) {
        return dao.batchUpdate(list);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'setting:'+#result.key", condition = "#result != null")
    public Setting insertSetting(Setting setting) {
        setting.setId(UUIDGenerator.getId());
        dao.insertSetting(setting);
        return dao.getSetting(setting.getKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Setting> getAllSetting() {
        return dao.getAllSetting();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "defaultCache", key = "'setting:'+#key", unless = "#result == null")
    public Setting getSetting(String key) {
        return dao.getSetting(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "defaultCache", key = "'setting:'+#result.key", condition = "#result != null")
    public Setting updateSetting(Setting setting) {
        dao.updateSetting(setting);
        return dao.getSetting(setting.getKey());
    }
}
