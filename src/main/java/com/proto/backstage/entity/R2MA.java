package com.proto.backstage.entity;

/**
 * Created by yitao on 2016/6/17.
 */
public class R2MA extends BaseDataEntity {
    private String roleId;
    private String moduleId;
    private String actionId;

    public R2MA() {
    }

    public R2MA(String roleId, String moduleId, String actionId) {
        this.roleId = roleId;
        this.moduleId = moduleId;
        this.actionId = actionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}
