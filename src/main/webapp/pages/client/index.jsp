<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@include file="/pages/common/pathhead.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("button.additem").click(function () {
				var attr = $(this).attr("bookid");
				$.getJSON("${basepath}cartservlet","action=addToCart&bookid=" + attr,function (data) {
					$("#countspn").text("您的购物车有" + data.count + "件商品");
					$("#namespan").html("您刚刚将"<<span stul></span>);
				})
			})

			$("a.logout").click(function () {
				return confirm("确定退出登录吗?");
			})

		})

	</script>
</head>
<body>


<div id="header">
	<img class="logo_img" style="size: A3" alt="" src="static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<div>
		<%--如果用户还没有登录，显示     【登录 和注册的菜单】 --%>
		<c:if test="${empty sessionScope.user}">
			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
		</c:if>
		<%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
		<c:if test="${not empty sessionScope.user}">
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临易书城</span>
			<a href="orderservlet?action=checkmyorder">我的订单</a>
			<a class="logout" href="userservlet?action=logout">注销</a>&nbsp;&nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
		</c:if>


		<a href="pages/manager/manager.jsp">后台管理</a>
	</div>
</div>



<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookservlet" method="get">
					<input type="hidden" name="action" value="pagebyprice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" id="search-price" />
				</form>
			</div>
			<div style="text-align: center">

					<span style="color: red;" id="contspn">购物车为空!</span>
					<div id="namespan">
						您刚刚将<span style="color: red;"></span>
					</div>

			</div>

			<c:forEach items="${requestScope.page.pageItems}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="static/img/default.jpg" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button bookid="${book.id}" class="additem">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>

		<%@include file="/pages/common/page.jsp"%>

	</div>

<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>