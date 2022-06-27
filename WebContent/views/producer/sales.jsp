<%@page import="jp.co.aforce.beans.BuyedBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jp.co.aforce.beans.BuyedInfoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売り上げ</title>
<link rel="stylesheet" href="css/reset2.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
<link rel="stylesheet" href="css/table2.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("producer")) {
	response.sendRedirect("/ShoppingSite/views/login/login.jsp");
}
%>

<% int total = 0; %>
<%try{ %>
	<% if(session.getAttribute("salesMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("salesMessage") %></h3>
	<%}
	session.removeAttribute("salesMessage");
	%>
<%} catch (Exception e) {} %>

<br><p style="font-size: 40px">売り上げ</p><br>
<% BuyedInfoBean infoBean = (BuyedInfoBean) session.getAttribute("salesBean"); %>
<%if(infoBean.getArraySize() > 0) { %>

	<table border="1" class="sorttbl" id="myTable">
		<thead>
			<tr>
				<td onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(1)')">商品ID<i class="fa fa-sort"></i></td>
				<td onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(2)')">消費者ID<i class="fa fa-sort"></i></td>
				<td onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(3)')">価格<i class="fa fa-sort"></i></td>
				<td onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(4)')">数量<i class="fa fa-sort"></i></td>
				<td onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(5)')">小計<i class="fa fa-sort"></i></td>
			</tr>
		</thead>
		<tbody>
			<% for(BuyedBean bean : infoBean.getBuyedBeanArray()) { %>
			<tr class="item">
				<td><%=bean.getItemId() %></td>
				<td><%=bean.getConsumerId() %></td>
				<td><%=bean.getPrice() %>円</td>
				<td><%=bean.getQuantity() %></td>
				<td><%=bean.getPrice() * bean.getQuantity() %>円</td>
				<% total = total + (bean.getPrice() * bean.getQuantity()); %>
			</tr>
			<% } %>
		</tbody>
	</table>
<p class="total">合計:<%=total %>円</p>
<%} else {%>
	売り上げ情報は存在しません。
<% } %>

<script type="text/javascript" src="js/w3.js"></script>
<script src="js/header-7.js"></script>
</body>
</html>