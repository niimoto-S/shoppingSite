<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ！</title>
<link rel="stylesheet" href="css/reset.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("consumer")) {
	request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
}
%>
<br>
<p>おかえりなさい、${sessionScope.userName}さん！</p>
<br>
<p>
<a href="consumer_cart.jsp">
    <button type="button">カート</button>
</a>
<a href="searchAllServlet">
    <button type="button">商品</button>
</a>
<a href="buyedSearchServlet">
    <button type="button">購入済み商品閲覧</button>
</a>
<a href="logoutServlet">
    <button type="button">ログアウト</button>
</a>
</p>
<script src="js/header-7.js"></script>
</body>
</html>