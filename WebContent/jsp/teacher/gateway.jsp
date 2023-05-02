<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp"%>
<%-- 验证教师权限的 --%>
<%
if(userBean.getRole() != RoleConfig.TEACHER) {
	if(userBean.getRole() == RoleConfig.STUDENT) {
		forwardInfoTipsPage("没有教师权限", UriConfig.INDEX);
	} else {
		forwardInfoTipsPage("非教师账号，无法访问", UriConfig.INDEX);
	}
	return;
}
%>