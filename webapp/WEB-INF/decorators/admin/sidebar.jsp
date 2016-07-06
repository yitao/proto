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
  <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200"
      style="padding-top: 20px" id="sidebarNav">
    <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
    <li class="sidebar-toggler-wrapper hide">
      <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
      <div class="sidebar-toggler"></div>
      <!-- END SIDEBAR TOGGLER BUTTON -->
    </li>
    <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
    <li class="sidebar-search-wrapper">
      <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
      <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
      <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
      <form class="sidebar-search  " action="page_general_search_3.html" method="POST">
        <a href="javascript:;" class="remove">
          <i class="icon-close"></i>
        </a>

        <div class="input-group">
          <input type="text" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                  <a href="javascript:;" class="btn submit">
                    <i class="icon-magnifier"></i>
                  </a>
              </span>
        </div>
      </form>
      <!-- END RESPONSIVE QUICK SEARCH FORM -->
    </li>
    <li class="nav-item">
      <a href="${ctx}/admin/index.do" class="nav-link">
        <i class="icon-home"></i>
        <span class="title">首页</span>
      </a>
    </li>
    <li class="heading">
      <h3 class="uppercase">普通管理</h3>
    </li>
    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-user"></i>
        <span class="title">用户管理</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">
        <li class="nav-item  ">
          <a href="${ctx}/admin/user/cardList.do" class="nav-link ">
            <span class="title">用户名片管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/user/list.do" class="nav-link ">
            <span class="title">会员账号管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/aggregationLog/list.do" class="nav-link ">
            <span class="title">聚合日志</span>
          </a>
        </li>
        <%--<li class="nav-item  ">
          <a href="${ctx}/admin/user/manage.do" class="nav-link ">
            <span class="title">用户管理</span>
          </a>
        </li>--%>
      </ul>
    </li>
    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-briefcase"></i>
        <span class="title">公司管理</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">
        <li class="nav-item  ">
          <a href="${ctx}/admin/company/list.do" class="nav-link ">
            <span class="title">公司管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/template/list.do" class="nav-link ">
            <span class="title">模板管理</span>
          </a>
        </li>
        <%--<li class="nav-item  ">
          <a href="${ctx}/admin/exinfo/list.do" class="nav-link ">
            <span class="title">公司广告管理</span>
          </a>
        </li>--%>
        <<li class="nav-item  ">
          <a href="${ctx}/admin/fieldType/list.do" class="nav-link ">
            <span class="title">属性类型管理</span>
          </a>
        </li>
      </ul>
    </li>

    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-book-open"></i>
        <span class="title">报表分析</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">
        <!--针对于 公司下所有员工所有添加的名片数据 关系链分析-->
        <li class="nav-item  ">
          <a href="${ctx}/admin/report/relationChain.do" class="nav-link ">
            <span class="title">企业关系链</span>
          </a>
        </li>
        <!-- 针对于 公司下所有员工所添加的名片数据 总和 按照时间维度分析-->
        <li class="nav-item  ">
          <a href="${ctx}/admin/report/companyBusinessLiveness.do" class="nav-link ">
            <span class="title">企业商务活跃度</span>
          </a>
        </li>
        <!-- 针对于 公司下所有员工所添加的名片数据 按照时间维度分析-->
        <li class="nav-item  ">
          <a href="${ctx}/admin/report/userBusinessLiveness.do" class="nav-link ">
            <span class="title">用户商务活跃度</span>
          </a>
        </li>
        <!-- 针对于 公司下所有员工所添加的名片数据总和 分析对应环保指数-->
        <li class="nav-item  ">
          <a href="${ctx}/admin/report/environmentIndex.do" class="nav-link ">
            <span class="title">环保指数</span>
          </a>
        </li>
      </ul>
    </li>

    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-call-end"></i>
        <span class="title">渠道管理</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">
        <li class="nav-item  ">
          <a href="${ctx}/admin/channel/deviceChannelUserList.do" class="nav-link ">
            <span class="title">分销渠道用户管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/channel/deviceChannelDeviceList.do" class="nav-link ">
            <span class="title">分销渠道设备管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/channel/deviceChannelReport.do" class="nav-link ">
            <span class="title">分销渠道报表分析</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/channel/promotionChannelUserList.do" class="nav-link ">
            <span class="title">推广渠道用户管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/channel/promotionChannelReport.do" class="nav-link ">
            <span class="title">推广渠道报表管理</span>
          </a>
        </li>
      </ul>
    </li>

    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-pin"></i>
        <span class="title">微信管理</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">
        <li class="nav-item  ">
          <a href="${ctx}/admin/device/list.do" class="nav-link ">
            <span class="title">设备管理</span>
          </a>
        </li>
      </ul>
    </li>
    <li class="heading">
      <h3 class="uppercase">系统管理</h3>
    </li>
    <li class="nav-item  ">
      <a href="javascript:;" class="nav-link nav-toggle">
        <i class="icon-settings"></i>
        <span class="title">后台管理</span>
        <span class="arrow"></span>
      </a>
      <ul class="sub-menu">

        <li class="nav-item  ">
          <a href="${ctx}/admin/manage/role/toManageRole.do" class="nav-link ">
            <span class="title">角色管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/manage/adminUser/list.do" class="nav-link ">
            <span class="title">管理员管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/permission/toManageMA.do" class="nav-link ">
            <span class="title">模块管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/permission/toManageRMA.do" class="nav-link ">
            <span class="title">权限管理</span>
          </a>
        </li>
        <%--<li class="nav-item  ">
          <a href="${ctx}/admin/permission/toManageAR.do" class="nav-link ">
            <span class="title">账号角色管理</span>
          </a>
        </li>--%>
        <li class="nav-item  ">
          <a href="${ctx}/admin/permission/toManageR2MA.do" class="nav-link ">
            <span class="title">模块可见性管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/permission/toManageR2R.do" class="nav-link ">
            <span class="title">角色可见性管理</span>
          </a>
        </li>

        <li class="nav-item  ">
          <a href="${ctx}/admin/sys/setting/list.do" class="nav-link ">
            <span class="title">系统参数管理</span>
          </a>
        </li>
        <li class="nav-item  ">
          <a href="${ctx}/admin/sysdict/list.do" class="nav-link ">
            <span class="title">系统字典管理</span>
          </a>
        </li>
      </ul>
    </li>
    <li class="nav-item  ">
      <a href="${ctx}/admin/auditLog/list.do" class="nav-link nav-toggle">
        <i class="icon-notebook"></i>
        <span class="title">日志审核</span>
      </a>
    </li>
    <li class="nav-item  ">
      <a href="${ctx}/admin/manage/password/modifyPassword.do" class="nav-link">
        <i class="icon-key"></i>
        <span class="title">修改密码</span>
      </a>
    </li>
  </ul>
  <!-- END SIDEBAR MENU -->
  <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->