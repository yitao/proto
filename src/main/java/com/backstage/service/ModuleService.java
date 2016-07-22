package com.backstage.service;

import com.backstage.dao.ActionDao;
import com.backstage.dao.ModuleDao;
import com.backstage.dao.R2MADao;
import com.backstage.dao.RMADao;
import com.backstage.entity.Action;
import com.backstage.entity.Module;
import com.backstage.entity.R2MA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/6/14.
 */
@Service
public class ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private ActionDao actionDao;
    @Autowired
    private RMADao rmaDao;
    @Autowired
    private R2MADao r2maDao;

    public Module insertModule(Module module) {
        moduleDao.save(module);
        return module;
    }

    public Module updateModule(Module module) {
        moduleDao.update(module);
        return module;
    }

    public void deleteModule(String moduleId) {
        moduleDao.delete(moduleId, true);
        actionDao.resetAllByModuleId(moduleId);
        rmaDao.deleteByModuleId(moduleId);
    }

    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    public List<Module> findAllMA() {
        List<Module> result = moduleDao.findAll();
        for (Module module : result) {
            List<Action> actions = actionDao.findAllByModuleId(module.getId());
            module.setActions(actions);
        }
        return result;
    }

    public List<Module> findAllMA4Opened(String roleId) {
        List<R2MA> r2mas = r2maDao.findAllByRoleId(roleId);
        List<Module> result = new ArrayList<>();
        Map<String, Module> moduleMap = new HashMap<>();
        Map<String, List<Action>> actionMap = new HashMap<>();
        for (R2MA r2ma : r2mas) {
            String moduleId = r2ma.getModuleId();
            String actionId = r2ma.getActionId();
            if (!moduleMap.containsKey(moduleId)) {
                Module module = moduleDao.get(moduleId);
                moduleMap.put(moduleId, module);
            }
            if (!actionMap.containsKey(moduleId)) {
                List<Action> actions = new ArrayList<>();
                Action action = actionDao.get(actionId);
                actions.add(action);
                actionMap.put(moduleId, actions);
            } else {
                List<Action> actions = actionMap.get(moduleId);
                Action action = actionDao.get(actionId);
                actions.add(action);
            }
        }
        for (String moduleId : moduleMap.keySet()) {
            Module module = moduleMap.get(moduleId);
            List<Action> actions = actionMap.get(moduleId);
            module.setActions(actions);
            result.add(module);
        }
        return result;
    }

}
