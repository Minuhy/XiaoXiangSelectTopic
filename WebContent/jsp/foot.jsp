<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String currentPath = request.getContextPath();%>

<div id="footer">
	<hr/>
	<div class="container text-center">
		<p class="muted margin-5p-up">由 <a href="https://space.bilibili.com/32778000">敏Ymm</a> 使用 <a href="https://www.java.com/zh-CN/">Java</a> 制作</p>
    </div>
</div>

<!-- 引入JavaScript脚本文件 -->
<!-- 放置在文档末尾，以便页面加载更快 -->

<!-- jquery 和 bootstrap -->
<script src="<%= currentPath %>/common/jquery-1.9.1/jquery.js"></script>
<script src="<%= currentPath %>/common/bootstrap-2.3.2/js/bootstrap.js"></script>

<!-- 其他JS代码 -->
