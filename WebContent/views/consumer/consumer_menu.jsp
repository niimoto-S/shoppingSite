<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ！</title>
</head>
<body>

<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("consumer")) {
	response.sendRedirect("/ShoppingSite/views/login/login.jsp");
}
%>

おかえりなさい、${sessionScope.userName}さん！
<p>
<a href="consumer_cart.jsp">
    <button type="button">カート</button>
</a>
<a href="../../searchAllServlet">
    <button type="button">商品</button>
</a>
<a href="../../buyedSearchServlet">
    <button type="button">購入済み商品閲覧</button>
</a>
<a href="../login/login.jsp">
    <button type="button">ログアウト</button>
</a>
</p>

</body>
</html>