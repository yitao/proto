<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="page-header navbar navbar-fixed-top">
  <!-- BEGIN HEADER INNER -->
  <div class="page-header-inner ">
    <!-- BEGIN LOGO -->
    <div class="page-logo">
      <a href="index.html">
        <img src="${app_static}/metronic/assets/layouts/layout/img/logo.png" alt="logo" class="logo-default"/> </a>

      <div class="menu-toggler sidebar-toggler"></div>
    </div>
    <!-- END LOGO -->
    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
    <!-- END RESPONSIVE MENU TOGGLER -->
    <!-- BEGIN TOP NAVIGATION MENU -->
    <div class="top-menu">
      <ul class="nav navbar-nav pull-right">
        <!-- BEGIN USER LOGIN DROPDOWN -->
        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
        <li class="dropdown dropdown-user">
          <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
            <!--img alt="" class="img-circle" src="${app_static}/metronic/assets/layouts/layout/img/avatar3_small.jpg"/-->
            <span class="username username-hide-on-mobile"> <%=request.getSession().getAttribute("DEVICE_CHANNEL_USERNAME")%> </span>
            <i class="fa fa-angle-down"></i>
          </a>
          <ul class="dropdown-menu dropdown-menu-default">
            <li>
              <a href="${ctx}/deviceChannel/logout.do">
                <i class="icon-key"></i> 注销 </a>
            </li>
          </ul>
        </li>
        <!-- END USER LOGIN DROPDOWN -->
        <!-- BEGIN QUICK SIDEBAR TOGGLER -->
        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
        <%--
        <li class="dropdown dropdown-quick-sidebar-toggler">
          <a href="javascript:;" class="dropdown-toggle">
            <i class="icon-logout"></i>
          </a>
        </li>
        --%>
        <!-- END QUICK SIDEBAR TOGGLER -->
      </ul>
    </div>
    <!-- END TOP NAVIGATION MENU -->
  </div>
  <!-- END HEADER INNER -->
</div>