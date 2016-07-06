<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!-- BEGIN SIDEBAR -->
<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
        data-slide-speed="200"
        style="padding-top: 20px" id="sidebarNav">
        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
        <li class="sidebar-toggler-wrapper hide">
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler"></div>
            <!-- END SIDEBAR TOGGLER BUTTON -->
        </li>
        <li class="nav-item">
            <a href="${ctx}/company/index.do" class="nav-link">
                <i class="icon-home"></i>
                <span class="title">首页</span>
            </a>
        </li>
        <li class="heading">
            <h3 class="uppercase">普通管理</h3>
        </li>
        <li class="nav-item  ">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-briefcase"></i>
                <span class="title">公司管理</span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
                <li class="nav-item  ">
                    <a href="${ctx}/company/edit.do" class="nav-link ">
                        <span class="title">基础信息</span>
                    </a>
                </li>
                <li class="nav-item  ">
                    <a href="${ctx}/company/member/edit.do" class="nav-link ">
                        <span class="title">成员管理</span>
                    </a>
                </li>
                <li class="nav-item  ">
                    <a href="${ctx}/company/exinfo/list" class="nav-link ">
                        <span class="title">公司广告</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item  ">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-book-open"></i>
                <span class="title">公司报表分析</span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
                <!--针对于 公司下所有员工所有添加的名片数据 关系链分析-->
                <li class="nav-item  ">
                    <a href="${ctx}/company/report/relationChain.do" class="nav-link ">
                        <span class="title">企业关系链</span>
                    </a>
                </li>
                <!-- 针对于 公司下所有员工所添加的名片数据 总和 按照时间维度分析-->
                <li class="nav-item  ">
                    <a href="${ctx}/company/report/companyBusinessLiveness.do" class="nav-link ">
                        <span class="title">企业商务活跃度</span>
                    </a>
                </li>
                <!-- 针对于 公司下所有员工所添加的名片数据 按照时间维度分析-->
                <li class="nav-item  ">
                    <a href="${ctx}/company/report/userBusinessLiveness.do" class="nav-link ">
                        <span class="title">用户商务活跃度</span>
                    </a>
                </li>
                <!-- 针对于 公司下所有员工所添加的名片数据总和 分析对应环保指数-->
                <%--<li class="nav-item  ">
                  <a href="${ctx}/company/report/environmentIndex.do" class="nav-link ">
                    <span class="title">环保指数</span>
                  </a>
                </li>--%>
            </ul>
        </li>
        <li class="nav-item  ">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-user"></i>
                <span class="title">账号管理</span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
                <li class="nav-item  ">
                    <a href="${ctx}/company/manage/password/modifyPassword.do" class="nav-link">
                        <i class="icon-key"></i>
                        <span class="title">修改密码</span>
                    </a>
                </li>
                <li class="nav-item  ">
                    <a href="${ctx}/company/manage/account/toCreateChildAccount.do" class="nav-link">
                        <i class="icon-wrench"></i>
                        <span class="title">子账号管理</span>
                    </a>
                </li>
                <li class="nav-item  ">
                    <a href="${ctx}/company/manage/account/toManageRole.do" class="nav-link">
                        <i class="icon-wrench"></i>
                        <span class="title">角色管理</span>
                    </a>
                </li>
                <li class="nav-item  ">
                    <a href="${ctx}/company/manage/account/toManageRMA.do" class="nav-link">
                        <i class="icon-wrench"></i>
                        <span class="title">权限管理</span>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- END SIDEBAR MENU -->
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->