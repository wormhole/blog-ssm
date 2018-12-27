package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * 博客配置信息实体类
 *
 * @author 凉衫薄
 */
public class Setting implements Serializable {

    private String id;
    private String name;
    private String value;

    public Setting(){

    }

    public Setting(String id, String name, String value) {
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
}
