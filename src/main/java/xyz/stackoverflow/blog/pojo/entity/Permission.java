package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 权限实体类
 */
public class Permission implements Serializable {

    protected String id;
    protected String permissionName;
    protected String permissionCode;

    public Permission(){

    }

    public Permission(String id, String permissionName, String permissionCode) {
        this.id = id;
        this.permissionName = permissionName;
        this.permissionCode = permissionCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
