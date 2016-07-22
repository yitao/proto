package com.backstage.dao;

import com.backstage.entity.RMA;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface RMADao extends BaseSqlDao<RMA, String>{

    List<RMA> findAllByRoleId(String roleId);

    void delete(RMA rma);

    void deleteByRoleId(String roleId);

    void deleteByModuleId(String moduleId);

    void deleteByActionId(String actionId);

    List<String> findAllActionByAccount(String accountId);

    List<String> findAllExcludeActionByAccount(String accountId);

}
