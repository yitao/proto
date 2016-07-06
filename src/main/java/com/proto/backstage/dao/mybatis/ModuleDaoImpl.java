package com.proto.backstage.dao.mybatis;

import com.yt.permission.dao.ModuleDao;
import com.yt.permission.entity.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_ModuleDaoImpl")
public class ModuleDaoImpl extends BaseSqlDaoImpl<Module,String> implements ModuleDao{


    @Override
    public Module findDefault() {
        Module module = null;
        module = getSqlSession().selectOne(className+".findDefault");
        return module;
    }
}
