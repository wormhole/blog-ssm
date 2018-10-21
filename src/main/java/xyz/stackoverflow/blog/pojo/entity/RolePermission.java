package xyz.stackoverflow.blog.pojo.entity;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 角色-权限表实体类
 */
public class RolePermission {
    protected String id;
    protected String roleId;
    protected String permissionId;

    public RolePermission(){

    }

    public RolePermission(String id, String roleId, String permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
