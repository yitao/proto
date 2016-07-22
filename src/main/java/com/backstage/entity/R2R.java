package com.backstage.entity;

/**
 * Created by yitao on 2016/6/17.
 */
public class R2R extends BaseDataEntity {
    private String roleId;
    private String openedRoleId;

    public R2R() {
    }

    public R2R(String roleId, String openedRoleId) {
        this.roleId = roleId;
        this.openedRoleId = openedRoleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOpenedRoleId() {
        return openedRoleId;
    }

    public void setOpenedRoleId(String openedRoleId) {
        this.openedRoleId = openedRoleId;
    }
}
