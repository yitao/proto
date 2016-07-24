package com.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */
public class Role extends BaseDataEntity {
    String name;
    String desc;
    Boolean inuse;

    @Transient
    List<Module> modules;
    @Transient
    List<Role> openedRoles;
    @Transient
    List<Module> openedModules;

    int state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean isInuse() {
        return inuse;
    }

    public void setInuse(Boolean inuse) {
        this.inuse = inuse;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Boolean getInuse() {
        return inuse;
    }

    public List<Role> getOpenedRoles() {
        return openedRoles;
    }

    public void setOpenedRoles(List<Role> openedRoles) {
        this.openedRoles = openedRoles;
    }

    public List<Module> getOpenedModules() {
        return openedModules;
    }

    public void setOpenedModules(List<Module> openedModules) {
        this.openedModules = openedModules;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Role clone(){

        return null;
    }
}
