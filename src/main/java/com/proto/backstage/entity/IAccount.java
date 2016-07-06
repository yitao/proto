package com.proto.backstage.entity;

/**
 * Created by and on 2016/6/28.
 */
public interface IAccount<PK> {
    PK getId();
    void setId(PK pk);

    String getUserName();
    void setUserName(String userName);

    String getPassword();
    void setPassword(String password);

}
