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
    <div class="container">
        <div class="text-center">
            <h1 class="padding-bottom">毕设选题系统</h1>
            <h2 class="form-signin-heading lead">登录</h2>
        </div>
        <form action="<%= currentPath %>/user/login" class="form-signin" method="post">
            <!-- 账号输入框 -->
            <div>
                <label>
                    账号：
                    <input 
                        value="admin"
                        type="text" 
                        class="input-block-level" 
                        name="username" 
                        placeholder="请输入账号" 
                        required="required" 
                        oninvalid="setCustomValidity('请输入你的账号');" 
                        oninput="setCustomValidity('');" 
                        autofocus="autofocus">
                </label>
            </div>
            <!-- 密码输入框 -->
            <div>
                <label>
                    密码：
                    <input 
                    	value="123456"
                        type="password" 
                        class="input-block-level" 
                        name="password" 
                        placeholder="请输入密码" 
                        required="required" 
                        oninvalid="setCustomValidity('请输入账号的密码');" 
                        oninput="setCustomValidity('');" 
                        autocomplete="off">
                </label>
            </div>
            <!-- 验证码输入框 -->
            <div>
                <table>
                    <tbody>
                        <tr>
                            <td class="input-td">
                                <label>
                                    验证码：
                                    <input 
                                    	value="a"
                                        type="text"  
                                        name="captcha"
                                        class="input-block-level"
                                        placeholder="请输入验证码" 
                                        required="required" 
                                        oninvalid="setCustomValidity('请输入右边图片验证码的结果');" 
                                        oninput="setCustomValidity('');">
                                </label>
                            </td>
                            <td>
                                <img 
                                    id="captchaImg" 
                                    alt="验证码" 
                                    src="<%= currentPath %>/util/captcha">
                            </td>
                        </tr>
                    </tbody>
                </table>
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
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
	<!-- 本页JS -->
	<script src="<%= currentPath %>/common/lectopic/js/login.js"></script>
</body>

</html>
