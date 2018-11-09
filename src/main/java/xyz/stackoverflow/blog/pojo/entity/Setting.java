package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * 博客配置信息实体类
 *
 * @author 凉衫薄
 */
public class Setting implements Serializable {

    private String id;
    private String key;
    private String value;

    public Setting(){

    }

    public Setting(String id, String key, String value) {
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
}
