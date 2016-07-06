package com.proto.backstage.dao.mybatis;

import com.yt.permission.dao.R2MADao;
import com.yt.permission.dao.R2RDao;
import com.yt.permission.entity.R2MA;
import com.yt.permission.entity.R2R;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_R2RDaoImpl")
public class R2RDaoImpl extends BaseSqlDaoImpl<R2R,String> implements R2RDao {
    @Override
    public void deleteByRoleId(String roleId) {
        getSqlSession().delete(className+".deleteByRoleId",roleId);
    }

    @Override
    public void deleteByOpenedRoleId(String openedRoleId) {
        getSqlSession().delete(className+".deleteByOpenedRoleId",openedRoleId);
    }

    @Override
    public void delete(R2R r2r) {
        getSqlSession().delete(className+".delete",r2r);
    }

    @Override
    public List<R2R> findAllByRoleId(String roleId) {
        return getSqlSession().selectList(className+".findAllByRoleId",roleId);
    }
}
