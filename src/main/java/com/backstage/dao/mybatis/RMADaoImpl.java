package com.backstage.dao.mybatis;

import com.backstage.dao.RMADao;
import com.backstage.entity.RMA;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_RMADaoImpl")
public class RMADaoImpl extends BaseSqlDaoImpl<RMA, String> implements RMADao {

    public List<RMA> findAllByRoleId(String roleId){
        List<RMA> rmas = getSqlSession().selectList(className + ".findRmaByRoleId" , roleId);
        return rmas;
    }

    @Override
    public void delete(RMA rma) {
        getSqlSession().delete(className+".delete",rma);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        getSqlSession().delete(className+".deleteByRoleId",roleId);
    }

    @Override
    public void deleteByModuleId(String moduleId) {
        getSqlSession().delete(className+".deleteByModuleId",moduleId);
    }

    @Override
    public void deleteByActionId(String actionId) {
        getSqlSession().delete(className+".deleteByActionId",actionId);
    }

    @Override
    public List<String> findAllActionByAccount(String accountId) {
        return getSqlSession().selectList(className+".findAllActionByAccount",accountId);
    }

    @Override
    public List<String> findAllExcludeActionByAccount(String accountId) {
        return getSqlSession().selectList(className + ".findAllExcludeActionByAccount", accountId);
    }
}
