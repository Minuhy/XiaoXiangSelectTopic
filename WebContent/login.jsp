<%@page import="minuhy.xiaoxiang.lectopic.config.CommonConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.util.TextUtil"%>
<%@page import="minuhy.xiaoxiang.lectopic.bean.UserBean"%>
<%@ page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base.jsp" %>
<%
// 判断是否登录，如果登录了，直接跳转到主页，不需要再次登录
Object obj = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
if(obj instanceof UserBean && ((UserBean) obj).getId() > 0){
	// 已经登录
	
	String prePage = TextUtil.getString(session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE), // 登录前访问的页面
			currentPath + UriConfig.INDEX); // 没有则为默认值

	session.removeAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE);

			
	if(CommonConfig.isDebug()){
		log.debug("已经登录：{}", prePage);
	}
	// 跳转到之前的页面
	response.sendRedirect(prePage);
}
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="登录" name="pageTitle"/>
	</jsp:include>
	
    <link href="<%= currentPath %>/common/lectopic/css/login.css" rel="stylesheet">
</head>

<body>

	<div class="login-plan">

        <div class="text-c">
            <h1 class="padding-bottom">毕设选题系统</h1>
            <h2>登录</h2>
        </div>

        <form class="layui-form"  action="<%= currentPath %>/user/login"  method="post">
            <div class="layui-form-item">
                <label>账号</label>
                <input 
                    class="layui-input" 
                    value="admin" 
                    type="text" 
                    name="username" 
                    placeholder="请输入账号"
                    required="required" 
                    oninvalid="setCustomValidity('请输入你的账号');" 
                    oninput="setCustomValidity('');"
                    autofocus="autofocus">
            </div>
            <div class="layui-form-item">
                <label>密码</label>
                <input 
                    class="layui-input" 
                    value="123456" 
                    type="password" 
                    name="password" 
                    placeholder="请输入密码"
                    required="required" 
                    oninvalid="setCustomValidity('请输入账号的密码');" 
                    oninput="setCustomValidity('');"
                    autocomplete="off">
            </div>
            <div class="layui-form-item">
                <div>
                    <label>验证码</label>
                </div>
                <div>
                    <input 
                        class="layui-input captcha-input" 
                        value="a" 
                        type="text" 
                        name="captcha" 
                        placeholder="请输入验证码"
                        required="required" 
                        oninvalid="setCustomValidity('请输入右边图片验证码的结果');"
                        oninput="setCustomValidity('');">
                    <div class="captcha-img">
                        <img 
                        	id="captchaImg" 
                        	alt="验证码" 
                        	src="<%= currentPath %>/util/captcha">
                    </div>
                </div>
            </div>

            <div class="layui-form-item text-c">
                <input class="layui-btn  login-btn" type="submit" value="登录"></input>
            </div>
        </form>


        <div class="text-c">
            <a id="btnHelp" data-method="offset" data-type="auto"
                class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius">没有账号？</a>
        </div>

    </div>
    
    
	<div class="padding-30all">
		<hr>
	</div>
	
	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
	<!-- 本页JS -->
	<script src="<%= currentPath %>/common/lectopic/js/login.js"></script>
	
	<script type="text/javascript">
		var notHaveAccountTitle = "没有账号？";
		var notHaveAccountContent = "请联系老师处理";
	</script>
</body>

</html>
