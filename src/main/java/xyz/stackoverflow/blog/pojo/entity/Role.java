package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 角色实体类
 */
public class Role implements Serializable {

    protected String id;
    protected String roleName;
    protected String roleCode;

    public Role(){

    }

    public Role(String id, String roleName, String roleCode) {
        this.id = id;
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
