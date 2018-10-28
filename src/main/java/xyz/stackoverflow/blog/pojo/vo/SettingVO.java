package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Setting;

public class SettingVO extends Setting {

    public SettingVO() {
    }

    public SettingVO(String id, String key, String value) {
        super(id, key, value);
    }

    /**
     * VO类转实体类
     *
     * @return
     */
    public Setting toSetting() {
        Setting setting = new Setting();
        setting.setId(id);
        setting.setKey(key);
        setting.setValue(value);
        return setting;
    }
}
