package com.backstage.service;

import com.backstage.dao.ARDao;
import com.backstage.dao.RMADao;
import com.backstage.entity.Role;
import com.backstage.dao.RoleDao;
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

    public Role insertRole(Role role) {
        roleDao.save(role);
        return role;
    }

    public Role updateRole(Role role) {
        roleDao.update(role);
        return role;
    }

    public void deleteRole(String roleId){
        roleDao.delete(roleId,true);
        arDao.deleteByRoleId(roleId);
        rmaDao.deleteByRoleId(roleId);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
    
}
