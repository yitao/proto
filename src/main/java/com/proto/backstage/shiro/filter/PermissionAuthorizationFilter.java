package com.proto.backstage.shiro.filter;

import com.saysth.admin.web.controller.BaseAdminController;
import com.saysth.core.web.controller.BaseCompanyUserController;
import com.yt.permission.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 权限验证过滤器
 * Created by yitao on 2016/6/12.
 */
public class PermissionAuthorizationFilter extends AuthorizationFilter {

    @Autowired
    private PermissionService permissionService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        try {
            Subject subject = getSubject(request, response);
            if(!subject.isAuthenticated()){
                return false;
            }
            String loginType = (String) subject.getSession().getAttribute(BaseAdminController.LOGIN_TYPE);
            String userId = null;
            if(StringUtils.equals(BaseAdminController.LOGIN_TYPE_ADMIN,loginType)){
                userId = (String) subject.getSession().getAttribute(BaseAdminController.ADMIN_USER_ID);
            }else{
                userId = (String ) subject.getSession().getAttribute(BaseCompanyUserController.COMPANY_USER_ID);
            }
            if(mappedValue!=null) {
                String[] rolesArray = (String[]) mappedValue;//无需验证角色的名称集合，例如：admin
                for (int i = 0; i < rolesArray.length; i++) {
                    if (subject.hasRole(rolesArray[i])) {
                        return true;
                    }
                }
            }
            //TODO 获取用户当前 访问的接口
            String url = ((ShiroHttpServletRequest) request).getRequestURI();
            String cp = ((ShiroHttpServletRequest) request).getContextPath();
            url = url.substring(cp.length());
            Pattern pattern = Pattern.compile("(.*)\\.(.*)");
            Matcher m = pattern.matcher(url);
            if(m.matches()){
                url = m.group(1);
            }

            //TODO 获取用户账号当前角色所拥有的所有权限接口
            List<String> actions = permissionService.findAllActionByAccount(userId);
            //TODO 所有用户权限包含当前访问的接口则通过
            for(String action : actions){
                action = action.replace("*","(.*)");
                pattern = Pattern.compile(action);
                m = pattern.matcher(url);
                if(m.matches()){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
