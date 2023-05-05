<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.CommonConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp" %>
<%
// student teacher admin 
// RoleConfig.STUDENT_S RoleConfig.TEACHER_S RoleConfig.ADMIN_S
String role = request.getParameter("role");

// my other
String panel = request.getParameter("panel");

if(!"my".equals(panel)){
	panel = "other";
}

String curPage = request.getParameter("curPage");

int maxPage=0,minPage=0;
if(curPage != null){
	if(curPage.contains(".")){
		String[] curPages = curPage.split(".");
		try{
			maxPage = Integer.parseInt(curPages[0]);
			minPage = Integer.parseInt(curPages[1]);
		}catch (NumberFormatException e){
			log.error("获取导航栏位置时出错：{}", e);
		}
	}else{
		try{
			maxPage = Integer.parseInt(curPage);
			minPage = 0;
		}catch (NumberFormatException e){
			log.error("获取导航栏位置时出错：{}", e);
		}
	}
}else{
	if(CommonConfig.isDebug()){
		log.debug("导航栏位置为空");
	}
}

%>

<!-- 上方导航栏 开始 -->
<div class="layui-header header header-demo">
    <div class="layui-fluid">
        <!-- 图标跟标题 -->
        <a class="logo" href="<%= currentPath %>/index.jsp" style="color:#fff; font-size: 20px; margin-left: 0;">
            <img src="<%= currentPath %>/common/lectopic/favicon/favicon.gif" style="width: 28px;height: 28px;" alt="layui">
            潇湘毕设选题系统
        </a>
        <!-- 上方导航菜单栏 -->
        <ul class="layui-nav" id="LAY_NAV_TOP">
			<% if("my".equals(panel)) { %>
				<% if(RoleConfig.STUDENT_S.equals(role)){ %>
	            	<!-- 学生 开始 -->
	            	<!-- 学生 结束 -->
	            <% }else if(RoleConfig.TEACHER_S.equals(role)){ %>
	            	<!-- 老师 开始 -->
	            	<!-- 老师 结束 -->
	            <% }else if(RoleConfig.ADMIN_S.equals(role)){ %>
		            <li class="layui-nav-item">
		                <a href="<%= currentPath %>/jsp/admin/index.jsp">管理</a>
		            </li>
	            <% } %>
        	<% } else if("other".equals(panel)) { %>
	            <li class="layui-nav-item">
	                <a href="<%= currentPath %>/jsp/common/my.jsp">我的</a>
	            </li>
            <% } %>
            <li id="logoutLi" class="layui-nav-item">
                <a id="logoutBtn" data-method="logout" href="javascript:;">退出</a>
            </li>
        </ul>
    </div>
</div>
<!-- 上方导航栏 结束 -->



<!-- 侧方导航栏 开始 -->
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree site-demo-nav">
            <% if("my".equals(panel)) { %>
				<li class="layui-nav-item <%= maxPage==1?"layui-this":"" %>">
                    <a href="<%= currentPath %>/jsp/common/my.jsp">我的资料</a>
                </li>

                <li class="layui-nav-item <%= maxPage==2?"layui-this":"" %>">
                    <a href="javascript:;">我的消息<span class="layui-badge">666469+</span></a>
                </li>

                <li class="layui-nav-item <%= maxPage==3?"layui-this":"" %>">
                    <a href="<%= currentPath %>/jsp/common/profile.jsp">修改基本资料</a>
                </li>
                
                <% if(RoleConfig.STUDENT_S.equals(role)){ %>
	                <li class="layui-nav-item <%= maxPage==4?"layui-this":"" %>">
	                    <a href="javascript:;">修改学生资料</a>
	                </li>
	            <% }else if(RoleConfig.TEACHER_S.equals(role)){ %>
	                <li class="layui-nav-item <%= maxPage==5?"layui-this":"" %>">
	                    <a href="javascript:;">修改教师资料</a>
	                </li>
	            <% } %>

                <li class="layui-nav-item <%= maxPage==6?"layui-this":"" %>">
                    <a href="<%= currentPath %>/jsp/common/password.jsp">修改登录密码</a>
                </li>
        	<% } else if("other".equals(panel)) { %>
        		<!-- 其他界面 开始 -->
	            <% if(RoleConfig.STUDENT_S.equals(role)){ %>
	            	<!-- 学生 开始 -->
	            	<!-- 学生 结束 -->
	            <% }else if(RoleConfig.TEACHER_S.equals(role)){ %>
	            	<!-- 老师 开始 -->
	            	<!-- 老师 结束 -->
	            <% }else if(RoleConfig.ADMIN_S.equals(role)){ %>
	            	<!-- 管理员 开始 -->
					<li class="layui-nav-item layui-this">
		                <a href="<%= currentPath %>/jsp/admin/index.jsp">首页</a>
		            </li>
		
		            <li class="layui-nav-item layui-nav-itemed">
		                <a href="javascript:;">管理</a>
		                <dl class="layui-nav-child">
		                    <dd>
		                        <a href="/v2/demo/grid.html">学生管理</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">教师管理</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">课题管理</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">选题管理</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">组织架构管理</a>
		                    </dd>
		                </dl>
		            </li>
		
		            <li class="layui-nav-item">
		                <a href="javascript:;">导入</a>
		                <dl class="layui-nav-child">
		                    <dd>
		                        <a href="/v2/demo/grid.html">学生资料导入</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">教师资料导入</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">课题资料导入</a>
		                    </dd>
		                </dl>
		            </li>
		            
		            <li class="layui-nav-item">
		                <a href="javascript:;">工具</a>
		                <dl class="layui-nav-child">
		                    <dd>
		                        <a href="/v2/demo/grid.html">重置账号密码</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/grid.html">快速选题</a>
		                    </dd>
		                    <dd>
		                        <a href="/v2/demo/admin.html">高级工具</a>
		                    </dd>
		                </dl>
		            </li>
		            
		            <li class="layui-nav-item">
		                <a href="javascript:;">系统设置</a>
		            </li>
		            <!-- 管理员 结束 -->
	            <% } %>
	            <!-- 其他界面 结束 -->
            <% } %>
            <li class="layui-nav-item" style="height: 30px; text-align: center"></li>
        </ul>
    </div>
</div>
<!-- 侧方导航栏 结束 -->
