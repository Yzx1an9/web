<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->

	<%@include file="/pages/common/pathhead.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>

</head>
<body>
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >

	<%--静态包含，登录 成功之后的菜单 --%>
	<%@include file="/pages/common/head.jsp"%>


</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>
		
		<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>