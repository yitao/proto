package com.backstage.dao;

import com.backstage.entity.AR;

/**
 * Created by yitao on 2016/5/19.
 */
public interface ARDao extends BaseSqlDao<AR, String>{

    void deleteByAccountId(String accountId);

    void deleteByRoleId(String roleId);

    void delete(AR ar);

}
