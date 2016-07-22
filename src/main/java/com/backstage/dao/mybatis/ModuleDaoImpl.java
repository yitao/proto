package com.backstage.dao.mybatis;

import com.backstage.dao.ModuleDao;
import com.backstage.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_ModuleDaoImpl")
public class ModuleDaoImpl extends BaseSqlDaoImpl<Module, String> implements ModuleDao {


    @Override
    public Module findDefault() {
        Module module = null;
        module = getSqlSession().selectOne(className+".findDefault");
        return module;
    }
}
