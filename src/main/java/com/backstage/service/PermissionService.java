package com.backstage.service;

import com.backstage.dao.*;
import com.backstage.entity.*;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2016/6/14.
 */
@Service
public class PermissionService {
    Logger logger = Logger.getLogger(PermissionService.class);
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private RMADao rmaDao;
    @Autowired
    private R2RDao r2rDao;
    @Autowired
    private R2MADao r2maDao;
    @Autowired
    private ARDao arDao;
    @Autowired
    private ActionDao actionDao;
    @Autowired
    private ModuleService moduleService;
    //    @Autowired
//    private RoleDao roleDao;
    //使用系统自身的role
    @Autowired
    private RoleDao roleDao;


    public void assignAR(String accountId, String roleId) {
        AR ar = new AR(accountId, roleId);
        arDao.insert(ar);
    }

    public void removeAR(String accountId, String roleId) {
        AR ar = new AR(accountId, roleId);
        arDao.delete(ar);
    }
    public void removeAR(String accountId) {
        arDao.deleteByAccountId(accountId);
    }

    public void assignRMA(String roleId, String moduleId, String actionId) {
        RMA rma = new RMA(roleId, moduleId, actionId);
        rmaDao.insert(rma);
    }

    public void assignRM(String roleId, String moduleId) {
        for (Action action : actionDao.findAllByModuleId(moduleId)) {
            String actionId = action.getId();
            assignRMA(roleId, moduleId, actionId);
        }
    }

    public void removeRM(String roleId, String moduleId) {
        for (Action action : actionDao.findAllByModuleId(moduleId)) {
            String actionId = action.getId();
            removeRMA(roleId, moduleId, actionId);
        }
    }

    public void removeRMA(String roleId, String moduleId, String actionId) {
        RMA rma = new RMA(roleId, moduleId, actionId);
        rmaDao.delete(rma);
    }

    public void assignR2R(String roleId, String openedRoleId) {
        R2R r2r = new R2R(roleId, openedRoleId);
        r2rDao.insert(r2r);
    }

    public void removeR2R(String roleId, String openedRoleId) {
        R2R r2r = new R2R(roleId, openedRoleId);
        r2rDao.delete(r2r);
    }

    public void assignR2MA(String roleId, String moduleId, String actionId) {
        R2MA r2ma = new R2MA(roleId, moduleId, actionId);
        r2maDao.insert(r2ma);
    }

    public void assignR2M(String roleId, String moduleId) {
        for (Action action : actionDao.findAllByModuleId(moduleId)) {
            String actionId = action.getId();
            assignR2MA(roleId, moduleId, actionId);
        }
    }

    public void removeR2M(String roleId, String moduleId) {
        for (Action action : actionDao.findAllByModuleId(moduleId)) {
            String actionId = action.getId();
            removeR2MA(roleId, moduleId, actionId);
        }
    }

    public void removeR2MA(String roleId, String moduleId, String actionId) {
        R2MA r2ma = new R2MA(roleId, moduleId, actionId);
        r2maDao.delete(r2ma);
    }

    public List<String> findAllExcludeActionByAccount(String accountId) {
        return rmaDao.findAllExcludeActionByAccount(accountId);
    }

    public List<String> findAllActionByAccount(String accountId) {
        return rmaDao.findAllActionByAccount(accountId);
    }

    /**
     * 查询所有角色以及其可见模块功能
     *
     * @return
     */
    public List<Role> queryR2R() {
        //查询当前用户角色所有可见角色
        List<Role> roles = roleDao.findAll();
        //查询当前用户角色所有可见模块，功能
        for (Role role : roles) {
            List<Role> openedRoles = new ArrayList<>();
            String roleId = role.getId();
            List<R2R> r2rs = r2rDao.findAllByRoleId(roleId);
            for (Role role2 : roles) {
                Role openedRole = role2.clone();
                for (R2R r2r : r2rs) {
                    if (StringUtils.equals(r2r.getOpenedRoleId(), openedRole.getId())) {
                        openedRole.setState(1);
                        break;
                    }
                }
                openedRoles.add(openedRole);
            }
            role.setOpenedRoles(openedRoles);
        }
        return roles;
    }

    /**
     * 查询所有角色以及其可见模块功能
     *
     * @return
     */
    public List<Role> queryrolewithprivilege(String userId) {
        boolean buildIn = false;
        List<Role> ownerRoles = new ArrayList<>();//roleDao.findAllRoleByAccountId(userId);
        Role owner = null;
        if(ownerRoles.size()>0){
            owner = ownerRoles.get(0);
        }
        for (Role role : ownerRoles) {
            /*if(role.isBuildIn()){
                buildIn = true;
                break;
            }*/
        }
        List<Role> roles = null;
        List<Module> modules = null;
        if(buildIn){
            roles = roleDao.findAll();
            modules = moduleService.findAllMA();
        }else{
            //查询当前用户角色所有可见角色
            roles =  new ArrayList<>();//roleDao.findAllOpenedRoleByAccountId(userId);
            //查询当前用户角色所有可见模块，功能
            modules =  moduleService.findAllMA4Opened(owner.getId());
        }
        int maCheckCount = 0;
        for (Role role : roles) {
            List<Module> nmodules = new ArrayList<>();
            String roleId = role.getId();
            List<RMA> rmas = rmaDao.findAllByRoleId(roleId);
            for (Module module : modules) {
                Module nmodule = module.clone();
                nmodules.add(nmodule);
                String moduleId = nmodule.getId();
                maCheckCount = 0;
                for (Action action : nmodule.getActions()) {
                    action.setState(0);
                    for (RMA rma : rmas) {
                        if (StringUtils.equals(rma.getActionId(), action.getId()) && StringUtils.equals(rma.getModuleId(), moduleId)) {
                            action.setState(1);
                            maCheckCount++;
                            break;
                        }
                    }
                }
                if (module.getActions().size() == 0) {
                    nmodule.setState(0);
                } else {
                    if (maCheckCount == module.getActions().size()) {
                        nmodule.setState(1);
                    } else if (maCheckCount > 0) {
                        nmodule.setState(2);
                    } else {
                        nmodule.setState(0);
                    }
                }
            }
            role.setModules(nmodules);
        }
        return roles;
    }

    /**
     * 查询所有角色以及所有模块功能
     *
     * @return
     */
    public List<Role> queryrolewithprivilege4show() {
        //查询所有角色
        List<Role> roles = roleDao.findAll();
        //查询所有模块功能
        List<Module> modules = moduleService.findAllMA();
        int maCheckCount = 0;
        for (Role role : roles) {
            List<Module> nmodules = new ArrayList<>();
            String roleId = role.getId();
            List<R2MA> rmas = r2maDao.findAllByRoleId(roleId);
            for (Module module : modules) {
                Module nmodule = module.clone();
                nmodules.add(nmodule);
                String moduleId = nmodule.getId();
                maCheckCount = 0;
                for (Action action : nmodule.getActions()) {
                    action.setState(0);
                    for (R2MA rma : rmas) {
                        if (StringUtils.equals(rma.getActionId(), action.getId()) && StringUtils.equals(rma.getModuleId(), moduleId)) {
                            action.setState(1);
                            maCheckCount++;
                            break;
                        }
                    }
                }
                if (module.getActions().size() == 0) {
                    nmodule.setState(0);
                } else {
                    if (maCheckCount == module.getActions().size()) {
                        nmodule.setState(1);
                    } else if (maCheckCount > 0) {
                        nmodule.setState(2);
                    } else {
                        nmodule.setState(0);
                    }
                }
            }
            role.setModules(nmodules);
        }
        return roles;
    }


}
