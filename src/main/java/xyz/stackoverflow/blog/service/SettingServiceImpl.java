package xyz.stackoverflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.stackoverflow.blog.dao.SettingDao;
import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.util.IdGenerator;

import java.util.List;

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
    @CachePut(value = "defaultCache", key = "'setting:'+#result.key", condition = "#result != null")
    public Setting insertSetting(Setting setting) {
        setting.setId(IdGenerator.getId());
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
