<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String currentPath = request.getContextPath(); %>
<%
String role = request.getParameter("role");
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
            <li class="layui-nav-item" data-dir="docs">
                <a href="/v2/docs/">我的</a>
            </li>
            <li id="logoutLi" class="layui-nav-item" data-dir="demo">
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
            <% if("student".equals(role)){ %>
            	<!-- 学生 开始 -->
            	<!-- 学生 结束 -->
            <% }else if("teacher".equals(role)){ %>
            	<!-- 老师 开始 -->
            	<!-- 老师 结束 -->
            <% }else if("admin".equals(role)){ %>
            	<!-- 管理员 开始 -->
				<li class="layui-nav-item layui-nav-itemed layui-this">
	                <a href="javascript:;">首页</a>
	            </li>
	
	            <li class="layui-nav-item">
	                <a href="javascript:;">学生</a>
	                <dl class="layui-nav-child">
	                    <dd>
	                        <a href="/v2/demo/grid.html">学生管理</a>
	                    </dd>
	                    <dd>
	                        <a href="/v2/demo/admin.html">学生导入</a>
	                    </dd>
	                </dl>
	            </li>
	
	            <li class="layui-nav-item">
	                <a href="javascript:;">教师</a>
	                <dl class="layui-nav-child">
	                    <dd>
	                        <a href="/v2/demo/grid.html">教师管理</a>
	                    </dd>
	                    <dd>
	                        <a href="/v2/demo/admin.html">教师导入</a>
	                    </dd>
	                </dl>
	            </li>
	            
	            <li class="layui-nav-item">
	                <a href="javascript:;">选题</a>
	                <dl class="layui-nav-child">
	                    <dd>
	                        <a href="/v2/demo/grid.html">选题管理</a>
	                    </dd>
	                    <dd>
	                        <a href="/v2/demo/grid.html">选题情况</a>
	                    </dd>
	                    <dd>
	                        <a href="/v2/demo/admin.html">选题导入</a>
	                    </dd>
	                </dl>
	            </li>
	            
	            <li class="layui-nav-item">
	                <a href="javascript:;">进度</a>
	                <dl class="layui-nav-child">
	                    <dd>
	                        <a href="/v2/demo/grid.html">进度管理</a>
	                    </dd>
	                </dl>
	            </li>
	
	            <li class="layui-nav-item">
	                <a href="javascript:;">组织结构</a>
	                <dl class="layui-nav-child">
	                    <dd>
	                        <a href="/v2/demo/grid.html">组织结构管理</a>
	                    </dd>
	                </dl>
	            </li>
	            <!-- 管理员 结束 -->
            <% } %>
            <li class="layui-nav-item" style="height: 30px; text-align: center"></li>
        </ul>
    </div>
</div>
<!-- 侧方导航栏 结束 -->
