<%--
  Created by IntelliJ IDEA.
  User: yzx
  Date: 2021/11/6
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/pages/common/pathhead.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<%--	<script type="text/javascript">
		$(function () {
			$("span.wel_word").click(function () {
				location.href = "${basepath}orderservlet?action=checkmyorder";
			})
		})
	</script>--%>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">我的订单</span>
    <%@ include file="/pages/common/head.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总价</td>
        </tr>
<%--        <tr>--%>
<%--            <td rowspan="${requestScope.orders.size() - 1}">${order.order_id}</td>--%>
<%--        </tr>--%>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.order_id}</td>
                <td>${order.name}</td>
                <td>${order.count}</td>
                <td>${order.price}</td>
                <td>${order.totalprice}</td>
            </tr>
        </c:forEach>

    </table>


</div>

<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>
