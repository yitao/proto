package com.backstage.entity;

/**
 * 角色和 模块（最小）功能之间关系
 * Created by yitao on 2016/5/17.
 */
@SuppressWarnings("serial")
public class RMA extends BaseDataEntity {
    String roleId;
    String moduleId;
    String actionId;

    public RMA() {
    }

    public RMA(String roleId, String moduleId, String actionId) {
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
