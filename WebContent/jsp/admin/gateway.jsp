<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp"%>
<%-- 验证管理员权限的 --%>
<%
if(userBean.getRole() != RoleConfig.ADMIN) {
	forwardInfoTipsPage("没有管理员权限", UriConfig.INDEX);
	return;
}
%>