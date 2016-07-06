package com.proto.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */

public class Account extends BaseDataEntity implements IAccount<String>{
    String account;
    String password;
    boolean inuse;
    @Transient
    List<Role> roles;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInuse() {
        return inuse;
    }

    public void setInuse(boolean inuse) {
        this.inuse = inuse;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUserName() {
        return account;
    }

    @Override
    public void setUserName(String userName) {
        this.account = userName;
    }
}
