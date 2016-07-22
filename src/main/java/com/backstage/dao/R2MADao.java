package com.backstage.dao;

import com.backstage.entity.R2MA;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface R2MADao extends BaseSqlDao<R2MA, String>{

    List<R2MA> findAllByRoleId(String roleId);

    void delete(R2MA rma);

    void deleteByRoleId(String roleId);

    void deleteByModuleId(String moduleId);

    void deleteByActionId(String actionId);

    List<String> findAllActionByAccount(String accountId);

}
