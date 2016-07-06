package com.proto.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */
public class Role extends BaseDataEntity implements IRole<String>{
    String name;
    String desc;
    String code;
    Boolean inuse;
    private boolean buildIn; // 是否超管组


    @Transient
    private int state;
    @Transient
    List<Module> modules;
    @Transient
    private List<IRole> openedRoles;

    public Role() {
    }

    public Role(String id,String name, String desc, String code, Boolean inuse, boolean buildIn) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.code = code;
        this.inuse = inuse;
        this.buildIn = buildIn;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getInuse() {
        return inuse;
    }

    public boolean isBuildIn() {
        return buildIn;
    }

    public void setBuildIn(boolean buildIn) {
        this.buildIn = buildIn;
    }

    public List<IRole> getOpenedRoles() {
        return openedRoles==null?new ArrayList<IRole>():openedRoles;
    }

    @Override
    public void setOpenedRoles(List<IRole> openedRoles) {
        this.openedRoles = openedRoles;
    }

    @Override
    public Role clone() {
        Role role = new Role(getId(),name,desc, code, inuse, buildIn);
        return role;
    }
}
