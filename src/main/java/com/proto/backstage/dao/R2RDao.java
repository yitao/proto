package com.proto.backstage.dao;


import com.proto.backstage.entity.R2R;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface R2RDao extends BaseSqlDao<R2R, String>{

    void deleteByRoleId(String roleId);

    void deleteByOpenedRoleId(String openedRoleId);

    void delete(R2R r2r);

    List<R2R> findAllByRoleId(String roleId);

}
