<%--
  Created by IntelliJ IDEA.
  User: yzx
  Date: 2021/11/1
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.requesturl}&pageNo=1">首页</a>
        <a href="${requestScope.page.requesturl}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>

    </c:if>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal <= 5}" >
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="pageno">
                <c:if test="${requestScope.page.pageNo == pageno}">
                    【${pageno}】
                </c:if>
                <c:if test="${requestScope.page.pageNo != pageno}">
                    <a href="${requestScope.page.requesturl}&pageNo=${pageno}">${pageno}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="pageno">
                        <c:if test="${requestScope.page.pageNo == pageno}">
                            【${pageno}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != pageno}">
                            <a href="${requestScope.page.requesturl}bookservlet?action=page&pageNo=${pageno}">${pageno}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                    <c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}" var="pageno">
                        <c:if test="${requestScope.page.pageNo == pageno}">
                            【${pageno}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != pageno}">
                            <a href="${requestScope.page.requesturl}&pageNo=${pageno}">${pageno}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="pageno">
                        <c:if test="${requestScope.page.pageNo == pageno}">
                            【${pageno}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != pageno}">
                            <a href="${requestScope.page.requesturl}&pageNo=${pageno}">${pageno}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>


    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.requesturl}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.requesturl}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="sub">

    <script type="text/javascript">
        $(function () {
            $("#sub").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${basepath}${requestScope.page.requesturl}&pageNo=" + pageNo;
            })
        });
    </script>
</div>

