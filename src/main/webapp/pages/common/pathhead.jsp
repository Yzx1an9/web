<%--
  Created by IntelliJ IDEA.
  User: yzx
  Date: 2021/11/2
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/";
    pageContext.setAttribute("basepath",basepath);
%>

<base href=<%=basepath%>>

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>