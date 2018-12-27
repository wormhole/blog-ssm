package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Setting;
import xyz.stackoverflow.blog.util.web.SuperVO;

/**
 * 博客配置信息VO类
 *
 * @author 凉衫薄
 */
public class SettingVO implements SuperVO {

    private String id;
    private String name;
    private String value;

    public SettingVO() {
    }

    public SettingVO(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        setting.setName(name);
        setting.setValue(value);
        return setting;
    }
}
