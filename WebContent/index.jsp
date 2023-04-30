<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="jsp/head.jsp">
		<jsp:param value="登录" name="pageTitle"/>
	</jsp:include>
    <link href="common/lectopic/css/login.css" rel="stylesheet">
</head>

<body>
    <div class="container text-center">
        <form class="form-signin">
            <h2 class="form-signin-heading">毕设选题系统</h2>
            <h2 class="form-signin-heading padding-bottom">登录</h2>
            <input type="text" class="input-block-level" placeholder="请输入账号">
            <input type="password" class="input-block-level" placeholder="请输入密码">
            <div class="padding-bottom">
                <button class="btn btn-large btn-primary" type="submit">登录</button>
            </div>
            <div>
                <a href="javascript:void();">没有账号？</a>
            </div>
        </form>
    </div> <!-- /container -->

	<!-- 页脚 -->
	<jsp:include page="jsp/foot.jsp"></jsp:include>
</body>

</html>
