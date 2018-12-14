package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.util.db.PageParameter;

import java.util.List;
import java.util.Map;

/**
 * 设置服务类接口
 *
 * @author 凉衫薄
 */
public interface SettingService {

    List<Setting> selectByPage(PageParameter pageParameter);

    List<Setting> selectByCondition(Map<String,String> searchMap);

    Setting selectById(String id);

    Setting insert(Setting setting);

    int batchInsert(List<Setting> list);

    Setting deleteById(String id);

    int batchDeleteById(List<String> list);

    Setting update(Setting setting);

    int batchUpdate(List<Setting> list);


    Setting insertSetting(Setting setting);

    List<Setting> getAllSetting();

    Setting getSetting(String key);

    Setting updateSetting(Setting setting);
}
