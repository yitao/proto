package com.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */
public class Module extends BaseLabelEntity implements Cloneable{
    String faModuleId;
    boolean isDefault;
    int state;

    public Module() {
    }

    public Module(String id, Long order, String icon, String label, String value, String hint, String desc, String faModuleId, boolean isDefault, int state) {
        super(id, order, icon, label, value, hint, desc);
        this.faModuleId = faModuleId;
        this.isDefault = isDefault;
        this.state = state;
    }

    @Transient
    List<Module> subModules;
    @Transient
    List<Action> actions;

    public String getFaModuleId() {
        return faModuleId;
    }

    public void setFaModuleId(String faModuleId) {
        this.faModuleId = faModuleId;
    }

    public List<Module> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<Module> subModules) {
        this.subModules = subModules;
    }

    public List<Action> getActions() {
        return actions==null?new ArrayList<Action>():actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Module clone() {
        Module module = new Module(id, order, icon, label, value, hint, desc, faModuleId,  isDefault,  state);
        if(this.actions!=null){
            List<Action> nas = new ArrayList<>();
            for(Action a : this.actions){
                Action na = a.clone();
                nas.add(na);
            }
            module.setActions(nas);
        }
        return module;
    }
}
