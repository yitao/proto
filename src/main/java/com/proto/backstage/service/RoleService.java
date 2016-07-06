package com.proto.backstage.service;

import com.proto.backstage.dao.*;
import com.proto.backstage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yitao on 2016/6/14.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RMADao rmaDao;
    @Autowired
    private ARDao arDao;

    public IRole insertRole(IRole role){
        roleDao.save(role);
        return role;
    }

    public IRole updateRole(IRole role){
        roleDao.update(role);
        return role;
    }

    public void deleteRole(String roleId){
        roleDao.delete(roleId,true);
        arDao.deleteByRoleId(roleId);
        rmaDao.deleteByRoleId(roleId);
    }

    public List<IRole> findAll(){
        return roleDao.findAll();
    }
    
}
