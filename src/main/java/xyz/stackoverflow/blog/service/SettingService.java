package xyz.stackoverflow.blog.service;

import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.pojo.po.SettingPO;

import java.util.List;
import java.util.Map;

/**
 * 设置服务类接口
 *
 * @author 凉衫薄
 */
public interface SettingService {

    List<SettingPO> selectByPage(Page page);

    List<SettingPO> selectByCondition(Map<String, Object> searchMap);

    SettingPO selectById(String id);

    SettingPO insert(SettingPO setting);

    int batchInsert(List<SettingPO> list);

    SettingPO deleteById(String id);

    int batchDeleteById(List<String> list);

    SettingPO update(SettingPO setting);

    int batchUpdate(List<SettingPO> list);

}
