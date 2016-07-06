package com.proto.backstage.dao;


import com.proto.backstage.entity.IRole;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface RoleDao extends BaseSqlDao<IRole, String>{

    List<IRole> findAllOpenedRoleByAccountId(String accountId);

    List<IRole> findAllRoleByAccountId(String accountId);
}
