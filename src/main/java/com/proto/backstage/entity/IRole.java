package com.proto.backstage.entity;

import java.util.List;

/**
 * Created by and on 2016/6/28.
 */
public interface IRole<PK> {
    PK getId();
    void setId(PK pk);

    void setState(int state);
    int getState();

    void setOpenedRoles(List<IRole>openedRoles);

    boolean isBuildIn();

    void setModules(List<Module> modules);

    IRole clone();
}
