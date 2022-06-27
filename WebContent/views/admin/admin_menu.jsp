<%@page import="jp.co.aforce.beans.RoleBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ！</title>
<link rel="stylesheet" href="css/reset2.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("admin")) {
	response.sendRedirect("../../logoutServlet");
}
%>

おかえりなさい、${sessionScope.userName}さん！

<p>
<a href="adminSearchItemServlet">
    <button type="button">商品検索</button>
</a>
<a href="adminSearchUserServlet">
    <button type="button">ユーザ検索</button>
</a>
</p>
<script src="js/header-7.js"></script>
</body>
</html>