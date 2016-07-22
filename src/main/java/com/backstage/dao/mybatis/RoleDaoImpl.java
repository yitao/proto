package com.backstage.dao.mybatis;

import com.backstage.entity.Role;
import com.backstage.dao.RoleDao;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_RoleDaoImpl")
public class RoleDaoImpl extends BaseSqlDaoImpl<Role,String> implements RoleDao{
}
