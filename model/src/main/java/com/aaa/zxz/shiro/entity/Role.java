package com.aaa.zxz.shiro.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private String roleChinesename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleChinesename() {
        return roleChinesename;
    }

    public void setRoleChinesename(String roleChinesename) {
        this.roleChinesename = roleChinesename == null ? null : roleChinesename.trim();
    }
}