package com.proto.backstage.entity;

import org.springframework.data.annotation.Transient;

/**
 * Created by yitao on 2016/5/11.
 */
public class Action extends BaseLabelEntity implements Cloneable{
    String moduleId;
    String action;
    boolean exclude;
    @Transient
    int state;

    public Action() {
    }

    public Action(String id, Long order, String icon, String label, String value, String hint, String desc, String moduleId, String action,boolean exclude, int state) {
        super(id, order, icon, label, value, hint, desc);
        this.moduleId = moduleId;
        this.action = action;
        this.exclude = exclude;
        this.state = state;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    @Override
    public Action clone() {
        Action a = new Action(id, order, icon, label, value, hint, desc, moduleId, action,exclude, state);
        return a;
    }
}
