package com.aaa.zxz.shiro.entity;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer id;

    private String permissionName;

    private String permissionChinesename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionChinesename() {
        return permissionChinesename;
    }

    public void setPermissionChinesename(String permissionChinesename) {
        this.permissionChinesename = permissionChinesename == null ? null : permissionChinesename.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) :
                that.permissionName != null)
            return false;
        return permissionChinesename != null ?
                permissionChinesename.equals(that.permissionChinesename) :
                that.permissionChinesename == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        result = 31 * result + (permissionChinesename != null ? permissionChinesename.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionChinesename='" + permissionChinesename + '\'' +
                '}';
    }
}