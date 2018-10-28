package xyz.stackoverflow.blog.dao;

import org.springframework.stereotype.Repository;
import xyz.stackoverflow.blog.pojo.entity.Setting;

import java.util.List;

/**
 * 设置信息表DAO
 *
 * @author 凉衫薄
 */
@Repository
public interface SettingDao {

    int insertSetting(Setting setting);

    List<Setting> getAllSetting();

    Setting getSetting(String key);

    int updateSetting(Setting setting);
}
