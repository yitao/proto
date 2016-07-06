package com.proto.backstage.dao.mybatis;

import com.proto.backstage.dao.ActionDao;
import com.proto.backstage.entity.Action;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_ActionDaoImpl")
public class ActionDaoImpl extends BaseSqlDaoImpl<Action,String> implements ActionDao {

    @Override
    public List<Action> findAllByModuleId(String moduleId) {
        List<Action> result = null;
        result = getSqlSession().selectList(className+".findAllByModuleId",moduleId);
        return result;
    }

    @Override
    public Action findOneByAction(String action) {
        Action result = null;
        result = getSqlSession().selectOne(className+".findOneByAction",action);
        return result;
    }

    @Override
    public void resetAllByModuleId(String moduleId) {
        getSqlSession().update(className+".resetAllByModuleId",moduleId);
    }
}
