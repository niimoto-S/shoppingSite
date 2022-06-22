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
if(roleBean == null || !roleBean.getRole().equals("producer")) {
	response.sendRedirect("/ShoppingSite/views/login/login.jsp");
}
%>

おかえりなさい、${sessionScope.userName}さん！
<p>
<a href="../../searchItemServlet">
    <button type="button">販売商品検索</button>
</a>
<a href="addItem.jsp">
    <button type="button">販売商品追加</button>
</a>
<a href="../../searchSalesServlet">
    <button type="button">売り上げ検索</button>
</a>
<a href="../login/login.jsp">
    <button type="button">ログアウト</button>
</a>
</p>

</body>
</html>