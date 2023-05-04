<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 设置管理员密码，不需要登录，只需要登录那边给session设置一个权限即可 --%>
<%@ include file="/jsp/base.jsp" %>
<%
Object obj = session.getAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
if (!(obj instanceof Boolean && ((Boolean) obj))) {
	System.out.println(currentPath);
	forwardInfoTipsPage("没有权限访问", UriConfig.LOGIN);
	return;
}
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="管理员注册" name="pageTitle"/>
	</jsp:include>
    <link href="<%= currentPath %>/common/lectopic/css/admin-register.css" rel="stylesheet">
</head>

<body>

    <div class="login-plan">

        <div class="text-c">
            <h1 class="padding-bottom">系统管理员账号注册</h1>
            <h2>为管理员账号设置一个新密码</h2>
        </div>

        <form class="layui-form" action="<%= currentPath %>/admin/register" method="post">
            
            <div class="layui-form-item">
                <label>请设置密码</label>
                <input 
                    class="layui-input" 
                    value="123456" 
                    type="password" 
                    name="password" 
                    placeholder="请输入密码"
                    required="required" 
                    oninvalid="setCustomValidity('请输入密码');" 
                    oninput="setCustomValidity('');"
                    autocomplete="off">
            </div>

            <div class="layui-form-item">
                <label>请确认密码</label>
                <input 
                    class="layui-input" 
                    value="123456" 
                    type="password" 
                    name="repasswd" 
                    placeholder="请再次输入密码"
                    required="required" 
                    oninvalid="setCustomValidity('请再次输入密码');" 
                    oninput="setCustomValidity('');"
                    autocomplete="off">
            </div>

            <div class="layui-form-item text-c">
                <input class="layui-btn  login-btn" type="submit" value="注册"></input>
            </div>
        </form>
    </div>
        
	<div class="padding-30all">
		<hr>
	</div>

	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
