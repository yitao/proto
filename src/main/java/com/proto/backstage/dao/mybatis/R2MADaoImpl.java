package com.proto.backstage.dao.mybatis;

import com.yt.permission.dao.R2MADao;
import com.yt.permission.dao.R2MADao;
import com.yt.permission.entity.R2MA;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_R2MADaoImpl")
public class R2MADaoImpl extends BaseSqlDaoImpl<R2MA,String> implements R2MADao {

    public List<R2MA> findAllByRoleId(String roleId){
        List<R2MA> rmas = getSqlSession().selectList(className + ".findRmaByRoleId" , roleId);
        return rmas;
    }

    @Override
    public void delete(R2MA rma) {
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
}
