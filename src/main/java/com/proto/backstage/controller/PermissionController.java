package com.proto.backstage.controller;

import com.proto.backstage.service.*;
import com.proto.backstage.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/6/14.
 */
@Controller("P_PermissionController")
@RequestMapping("/admin/permission")
public class PermissionController {
    Logger logger = Logger.getLogger(PermissionController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/toManageMA")
    public String toManagerMA(){
        return "/admin/permission/ma";
    }

    @RequestMapping("/toManageAR")
    public String toManagerAR(){
        return "/admin/permission/ar";
    }

    @RequestMapping("/toManageRMA")
    public String toManagerRMA(){
        return "/admin/permission/rma";
    }

    @RequestMapping("/toManageR2R")
    public String toManagerR2R(){
        return "/admin/permission/r2r";
    }

    @RequestMapping("/toManageR2MA")
    public String toManagerR2RMA(){
        return "/admin/permission/r2ma";
    }

    @RequestMapping("/listModule")
    @ResponseBody
    public Map<String,Object> listModule(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Module> moduleList = new ArrayList<Module>();
        Module module = new Module();
        module.setId("");
        module.setLabel("未分配功能");
        module.setOrder(0);
        moduleList.add(module);
        moduleList.addAll(moduleService.findAll());

        result.put("code",0);
        result.put("result",moduleList);
        return result;
    }

    @RequestMapping("/addModule")
    @ResponseBody
    public Map<String,Object> addModule(Module module){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            moduleService.insertModule(module);
            result.put("code",0);
            result.put("result",module);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加模块失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/updateModule")
    @ResponseBody
    public Map<String,Object> updateModule(Module module){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            moduleService.updateModule(module);
            result.put("code",0);
            result.put("result",module);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("更新模块失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/deleteModule")
    @ResponseBody
    public Map<String,Object> deleteModule(String id){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            if(StringUtils.isNotBlank(id)){
                moduleService.deleteModule(id);
                result.put("code",0);
            }else{
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("删除模块失败：{}",e);
        }
        return result;
    }

    @RequestMapping("/listActionByModuleId")
    @ResponseBody
    public Map<String,Object> listActionByModuleId(String moduleId){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Action> actionList = actionService.findAllByModuleId(moduleId);
        result.put("code",0);
        result.put("result",actionList);
        return result;
    }

    @RequestMapping("/addAction")
    @ResponseBody
    public Map<String,Object> addAction(Action action){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            actionService.insertAction(action);
            result.put("code",0);
            result.put("result",action);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加动作失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/updateAction")
    @ResponseBody
    public Map<String,Object> updateAction(Action action){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            actionService.updateAction(action);
            result.put("code",0);
            result.put("result",action);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加动作失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/deleteAction")
    @ResponseBody
    public Map<String,Object> deleteAction(String id){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            actionService.deleteAction(id);
            result.put("code",0);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加动作失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }


    //TODO 账号
    @RequestMapping("/listAccount")
    @ResponseBody
    public Map<String,Object> listAccount(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<IAccount> accountList = accountService.findAll();
        result.put("code",0);
        result.put("result",accountList);
        return result;
    }

    @RequestMapping("/addAccount")
    @ResponseBody
    public Map<String,Object> addAccount(Account account){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            accountService.insertAccount(account);
            result.put("code",0);
            result.put("result",account);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加账号失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/updateAccount")
    @ResponseBody
    public Map<String,Object> updateAccount(Account account){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            accountService.updateAccount(account);
            result.put("code",0);
            result.put("result",account);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("更新账号失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/deleteAccount")
    @ResponseBody
    public Map<String,Object> deleteAccount(String id){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            accountService.deleteAccount(id);
            result.put("code",0);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("删除账号失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    //TODO 角色
    @RequestMapping("/listRole")
    @ResponseBody
    public Map<String,Object> listRole(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<IRole> roleList = roleService.findAll();
        result.put("code",0);
        result.put("result",roleList);
        return result;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Map<String,Object> addRole(Role role){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            roleService.insertRole(role);
            result.put("code",0);
            result.put("result",role);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("添加角色失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Map<String,Object> updateRole(Role role){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            roleService.updateRole(role);
            result.put("code",0);
            result.put("result",role);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("更新角色失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public Map<String,Object> deleteRole(String id){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            roleService.deleteRole(id);
            result.put("code",0);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("删除角色失败：{}",e);
            result.put("code",-1);
        }
        return result;
    }

    //TODO 账号角色分配
    @RequestMapping("/assignAR")
    @ResponseBody
    public Map<String,Object> assignAR(String accountId,String roleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignAR(accountId,roleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配账号角色失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeAR")
    @ResponseBody
    public Map<String,Object> removeAR(String accountId,String roleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeAR(accountId,roleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("移除账号角色失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    //TODO 角色模块动作
    @RequestMapping("/assignRMA")
    @ResponseBody
    public Map<String,Object> assignRMA(String roleId,String moduleId,String actionId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignRMA(roleId,moduleId,actionId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配角色模块功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }
    @RequestMapping("/assignRM")
    @ResponseBody
    public Map<String,Object> assignRM(String roleId,String moduleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignRM(roleId,moduleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配角色模块功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeRMA")
    @ResponseBody
    public Map<String,Object> removeRMA(String roleId,String moduleId,String actionId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeRMA(roleId,moduleId,actionId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("移除角色模块功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeRM")
    @ResponseBody
    public Map<String,Object> removeRM(String roleId,String moduleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeRM(roleId,moduleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("移除角色模块功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/queryr2r")
    @ResponseBody
    public Map<String,Object> queryR2R(HttpServletRequest req){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            List<IRole> roles = permissionService.queryR2R();
            result.put("code",0);
            result.put("result",roles);
        } catch (Exception e) {
            logger.error("查询角色可见性功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/queryrolewithprivilege")
    @ResponseBody
    public Map<String,Object> queryrolewithprivilege(HttpServletRequest req){
        String userId = (String ) req.getSession().getAttribute("USER_ID");
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            List<IRole> roles = permissionService.queryrolewithprivilege(userId);
            result.put("code",0);
            result.put("result",roles);
        } catch (Exception e) {
            logger.error("查询角色权限功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }


    //TODO 角色可见性分配
    @RequestMapping("/assignR2R")
    @ResponseBody
    public Map<String,Object> assignR2R(String roleId,String openedRoleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignR2R(roleId,openedRoleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配角色可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeR2R")
    @ResponseBody
    public Map<String,Object> removeR2R(String roleId,String openedRoleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeAR(roleId,openedRoleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("角色可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    //TODO 角色模块动作
    @RequestMapping("/assignR2MA")
    @ResponseBody
    public Map<String,Object> assignR2MA(String roleId,String moduleId,String actionId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignR2MA(roleId,moduleId,actionId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配角色模块功能可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }
    @RequestMapping("/assignR2M")
    @ResponseBody
    public Map<String,Object> assignR2M(String roleId,String moduleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.assignR2M(roleId,moduleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("分配角色模块功能可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeR2MA")
    @ResponseBody
    public Map<String,Object> removeR2MA(String roleId,String moduleId,String actionId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeR2MA(roleId,moduleId,actionId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("移除角色模块功能可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/removeR2M")
    @ResponseBody
    public Map<String,Object> removeR2M(String roleId,String moduleId){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            permissionService.removeR2M(roleId,moduleId);
            result.put("code",0);
        } catch (Exception e) {
            logger.error("移除角色模块功能可见性失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/queryrolewithprivilege4show")
    @ResponseBody
    public Map<String,Object> queryrolewithprivilege4show(HttpServletRequest req){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            List<IRole> roles = permissionService.queryrolewithprivilege4show();
            result.put("code",0);
            result.put("result",roles);
        } catch (Exception e) {
            logger.error("查询角色权限功能失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

    @RequestMapping("/listCompanyUser")
    @ResponseBody
    public Map<String,Object> listCompanyUser(HttpServletRequest req){
        Map<String,Object> result = new HashMap<>();
        try{
            result.put("code",0);
        } catch (Exception e) {
            logger.error("查询公司账号失败：{}",e);
            //e.printStackTrace();
            result.put("code",-1);
        }
        return result;
    }

}
