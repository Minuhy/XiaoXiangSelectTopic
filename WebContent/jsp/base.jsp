<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.UriConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.enums.MessageState"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 这里定义一些JSP中常用的处理，本项目所有JSP均包含本JSP --%>
<%
this.request = request;
this.response = response;
this.currentPath = request.getContextPath();
%>
<%!
/**
 * 日志对象
 */
Logger log = LoggerFactory.getLogger(this.getClass());

/**
 * 当前应用路径
 */
String currentPath;

HttpServletRequest request;
HttpServletResponse response;

public final void forwardFailInfoPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.FAIL, info, uri, true);
}

public final void forwardErrorInfoPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.ERROR, info, uri, true);
}

public final void forwardInfoPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.INFO, info, uri, true);
}

public final void forwardSuccessInfoPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.SUCCESS, info, uri, true);
}

public final void forwardFailTipsPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.FAIL, info, uri, true);
}

public final void forwardErrorTipsPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.ERROR, info, uri, true);
}

public final void forwardInfoTipsPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.INFO, info, uri, true);
}

public final void forwardSuccessTipsPage(String info, String uri) throws ServletException, IOException {
	forwardMessagePage(MessageState.SUCCESS, info, uri, true);
}

/**
 * 转发到提示页面（自动跳转）
 * 
 * @param sta  状态
 * @param info 信息
 * @param uri  继续操作的页面
 * @throws ServletException 异常
 * @throws IOException      异常
 * @time 2023-4-30 0:27:13
 */
public final void forwardTipsPage(MessageState sta, String info, String uri) throws ServletException, IOException {
	forwardMessagePage(sta, info, uri, true);
}

/**
 * 转发到消息页面（不自动跳转）
 * 
 * @param sta  状态
 * @param info 信息
 * @param uri  继续操作的页面
 * @throws ServletException 异常
 * @throws IOException      异常
 * @time 2023-4-30 0:28:30
 */
public final void forwardInfoPage(MessageState sta, String info, String uri) throws ServletException, IOException {
	forwardMessagePage(sta, info, uri, false);
}

/**
 * 转发到消息页面
 * 
 * @param sta        状态
 * @param info       信息
 * @param uri        继续操作的页面
 * @param isAutoSkip 是否自动跳转
 * @throws ServletException 异常
 * @throws IOException      异常
 * @time 2023-4-30 0:27:52
 */
private final void forwardMessagePage(MessageState sta, String info, String uri, boolean isAutoSkip)
		throws ServletException, IOException {
	if(this.request==null || this.response==null){
		log.error("无法跳转到提示界面，请求对象和响应对象未初始化");
		return;
	}
	
	if(!(uri.startsWith("http://") || uri.startsWith("https://"))) {
		uri = currentPath + uri;
	}
	
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_STATE, sta);
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_INFO, info);
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_URI, uri);
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_SKIP, isAutoSkip);
	request.getRequestDispatcher(UriConfig.MESSAGE).include(request, response);
	if(isAutoSkip) {
		// response.setHeader("refresh", "3;url="+uri);
	}
}

%>