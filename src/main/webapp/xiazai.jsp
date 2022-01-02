<%--
  Created by IntelliJ IDEA.
  User: yzx
  Date: 2021/10/24
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pages/common/pathhead.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    $(function () {
        $("#submit").click(function () {
            var serialize = $("#form1").serialize();
            $.get("${basepath}/ajaxservlet","action=ajaxtest&" + serialize,function (data) {
                $("#msg").html("用户id:" + data.id + "用户账号名:" + data.username);
            },"json")
        })

        $("#postbtnjson").click(function () {
            $.getJSON("${basepath}/ajaxservlet","action=ajaxtest",function (data) {
                $("#msg").html("用户id:" + data.id + "用户账号名:" + data.username);
            })
        })





    })
</script>
<body>
    <a href="downloadServlet">下载链接</a>
    <a id="msg"></a>
    <br/>
    <form id="form1">
        用户名：<input name="username" type="text" /><br/>
        密码：<input name="password" type="password" /><br/>
    </form>
    <button id="submit">提交</button><br/>
    <div>
        <button id="btn">get请求</button>
        <button id="postbtnjson">postjson请求</button>
    </div>

</body>
</html>
