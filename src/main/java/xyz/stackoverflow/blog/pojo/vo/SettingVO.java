package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.util.AbstractVO;

/**
 * 博客配置信息VO类
 *
 * @author 凉衫薄
 */
public class SettingVO implements AbstractVO {

    private String id;
    private String key;
    private String value;

    public SettingVO() {
    }

    public SettingVO(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
