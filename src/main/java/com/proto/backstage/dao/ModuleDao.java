package com.proto.backstage.dao;

import com.proto.backstage.entity.Module;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface ModuleDao extends BaseSqlDao<Module, String>{

    Module findDefault();

}
