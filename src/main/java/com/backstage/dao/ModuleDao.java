package com.backstage.dao;

import com.backstage.entity.Module;

/**
 * Created by yitao on 2016/5/19.
 */
public interface ModuleDao extends BaseSqlDao<Module, String>{

    Module findDefault();

}
