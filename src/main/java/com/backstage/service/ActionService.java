package com.backstage.service;

import com.backstage.dao.RMADao;
import com.backstage.dao.ActionDao;
import com.backstage.entity.Action;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yitao on 2016/6/13.
 */
@Service
public class ActionService {
    Logger logger = Logger.getLogger(ActionService.class);
//    Log logger = LogFactory.getLog(ActionService.class);
    @Autowired
    private ActionDao actionDao;
    @Autowired
    private RMADao rmaDao;

    public Action insertIfNotExists(String action){
        //actionDao.findOneByAction(action);
        try {
            Action act = new Action();
            act.setLabel("");
            act.setValue("");
            act.setModuleId("");
            act.setHint("");
            act.setOrder(0);
            act.setAction(action);
            actionDao.save(act);
            return act;
        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("插入action失败：{}",e);
            logger.info("该action：" + action+" 已经存在");
        }
        return null;
    }

    public Action insertAction(Action action){
        actionDao.save(action);
        return action;
    }

    public Action updateAction(Action action){
        actionDao.update(action);
        return action;
    }

    public void deleteAction(String actionId){
        actionDao.delete(actionId,true);
        rmaDao.deleteByActionId(actionId);
    }

    public List<Action> findAll(){
        return actionDao.findAll();
    }

    public List<Action> findAllByModuleId(String moduleId){
        return actionDao.findAllByModuleId(moduleId);
    }

}
