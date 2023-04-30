<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String currentPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="jsp/head.jsp">
		<jsp:param value="登录" name="pageTitle"/>
	</jsp:include>
    <link href="<%= currentPath %>/common/lectopic/css/login.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="text-center">
            <h1 class="padding-bottom">毕设选题系统</h1>
            <h2 class="form-signin-heading lead">登录</h2>
        </div>
        <form action="<%= currentPath %>/user/login" class="form-signin" method="post">
            <!-- 账号输入框 -->
            <div>
                <label>账号：</label>
                <input type="text" class="input-block-level" name="username" placeholder="请输入账号" required>
            </div>
            <!-- 密码输入框 -->
            <div>
                <label>密码：</label>
                <input type="password" class="input-block-level" name="password" placeholder="请输入密码" required autocomplete="off">
            </div>
            <div class="text-right">
                <button class="btn btn-large btn-primary" type="submit">登录</button>
            </div>
        </form>

        <div class="text-center">
            <a href="#notHaveAccountModal" role="button" class="btn" data-toggle="modal">没有账号？</a>
        </div>

    </div> <!-- /container -->


    <!-- 没有账号模态框 -->
    <div id="notHaveAccountModal" class="modal hide fade" tabindex="-1" role="dialog"
        aria-labelledby="notHaveAccountModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="notHaveAccountModalLabel">没有账号</h3>
        </div>
        <div class="modal-body">
            <p>请联系老师处理</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">确定</button>
        </div>
    </div>

	<!-- 页脚 -->
	<jsp:include page="jsp/foot.jsp"></jsp:include>
</body>

</html>
