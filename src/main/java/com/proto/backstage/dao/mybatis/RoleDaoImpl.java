package com.proto.backstage.dao.mybatis;

import com.yt.permission.dao.RoleDao;
import com.yt.permission.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_RoleDaoImpl")
public class RoleDaoImpl extends BaseSqlDaoImpl<Role,String> implements RoleDao{
}
