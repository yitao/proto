<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.5.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
  <meta charset="utf-8"/>
  <title>${title_prefix} 管理后台</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta content="width=device-width, initial-scale=1" name="viewport"/>
  <meta content="" name="description"/>
  <meta content="" name="author"/>
  <!-- BEGIN GLOBAL MANDATORY STYLES -->
  <%--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>--%>
  <link href="${app_static}/metronic/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
  <!-- END GLOBAL MANDATORY STYLES -->
  <!-- BEGIN PAGE LEVEL PLUGINS -->

  <link rel="shortcut icon" href="${app_static}/favicon.ico"/>
  <!--[if lt IE 9]>
  <script src="${app_static}/metronic/assets/global/plugins/respond.min.js"></script>
  <script src="${app_static}/metronic/assets/global/plugins/excanvas.min.js"></script>
  <![endif]-->
  <!-- BEGIN CORE PLUGINS -->
  <script src="${app_static}/metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
  <!-- END CORE PLUGINS -->
  <!-- BEGIN PAGE LEVEL PLUGINS -->
  <!-- END PAGE LEVEL PLUGINS -->
  <!-- BEGIN THEME GLOBAL SCRIPTS -->
  <link href="${app_static}/metronic/assets/global/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css"/>

  <script src="${app_static}/metronic/assets/global/scripts/app.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/scripts/datatable.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/scripts/metronic.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/global/plugins/jstree/dist/jstree.min.js" type="text/javascript"></script>


  <script>
    //TODO:set the assets path
    App.setAssetsPath('${app_static}/metronic/assets/');
    App.setAppPath('${ctx}');
    $(function() {
      var url = window.location.href;
      $('#sidebarNav').find('li.nav-item').each(function() {
        var $this = $(this);
        var cUrl = $this.children('a').attr('href');
        if(cUrl != '') {
          if(url.indexOf(cUrl) != -1) {
            $this.addClass("start open active");
            $this.parents("li").addClass("start open active")
                    .children('a').children('span.arrow').addClass('open');
          }
        }
      });
    });
  </script>
  <!-- END THEME GLOBAL SCRIPTS -->
  <!-- BEGIN PAGE LEVEL SCRIPTS -->
  <sitemesh:write property='head'/>
  <!-- END PAGE LEVEL PLUGINS -->
  <!-- BEGIN THEME GLOBAL STYLES -->
  <link href="${app_static}/metronic/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
  <link href="${app_static}/metronic/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
  <!-- END THEME GLOBAL STYLES -->
  <!-- BEGIN THEME LAYOUT STYLES -->
  <link href="${app_static}/metronic/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
  <link href="${app_static}/metronic/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color"/>
  <link href="${app_static}/metronic/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css"/>
  <!-- END THEME LAYOUT STYLES -->
  <!-- END PAGE LEVEL SCRIPTS -->
  <!-- BEGIN THEME LAYOUT SCRIPTS -->
  <script src="${app_static}/metronic/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
  <script src="${app_static}/metronic/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
  <!-- END THEME LAYOUT SCRIPTS -->
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
<!-- BEGIN HEADER -->
<jsp:include page="header.jsp"/>
<!-- END HEADER -->
<!-- BEGIN HEADER & CONTENT DIVIDER -->
<div class="clearfix"></div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN SIDEBAR -->
  <div class="page-sidebar-wrapper">
    <jsp:include page="sidebar.jsp"/>
  </div>
  <!-- END SIDEBAR -->
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <sitemesh:write property='body'/>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
  <!-- BEGIN QUICK SIDEBAR -->
  <%--
  <jsp:include page="quick_sidebar.jsp"/>
  --%>
  <!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->
<jsp:include page="footer.jsp"/>
</body>
</html>