package xyz.stackoverflow.blog.pojo.po;

import java.io.Serializable;

/**
 * 角色实体类
 *
 * @author 凉衫薄
 */
public class RolePO implements Serializable {

    private String id;
    private String name;
    private String code;

    public RolePO() {

    }

    public RolePO(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
