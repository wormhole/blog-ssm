package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.pojo.entity.Setting;

import java.util.List;

/**
 * 设置服务类接口
 *
 * @author 凉衫薄
 */
public interface SettingService {

    Setting insertSetting(Setting setting);

    List<Setting> getAllSetting();

    Setting getSetting(String key);

    Setting updateSetting(Setting setting);
}
