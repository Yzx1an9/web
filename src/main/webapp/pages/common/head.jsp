<%--
  Created by IntelliJ IDEA.
  User: yzx
  Date: 2021/11/2
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
        $(function () {
                $("a.logout").click(function () {
                        return confirm("确定退出登录吗?");
                })
        })
</script>
        <div>
            <c:if test="${empty requestScope.operate}">
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临易书城</span>
                <a href="orderservlet?action=checkmyorder">我的订单</a>
                <a class="logout" href="userservlet?action=logout">注销</a>&nbsp;&nbsp;
                <a href="index.jsp">返回商城</a>
            </c:if>

            <c:if test="${not empty requestScope.operate}">
                <a href="orderservlet?action=showalluserorder">返回</a>
            </c:if>



        </div>
