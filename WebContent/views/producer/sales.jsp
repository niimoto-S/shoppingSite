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
</head>
<body>
<% int total = 0; %>
<%try{ %>
	<% if(session.getAttribute("salesMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("salesMessage") %></h3>
	<%}
	session.removeAttribute("salesMessage");
	%>
<%} catch (Exception e) {} %>

	売り上げ

<% BuyedInfoBean infoBean = (BuyedInfoBean) session.getAttribute("salesBean"); %>
<%if(infoBean.getArraySize() > 0) { %>

	<table border="1">
		<thead>
			<tr>
				<td>商品ID</td>
				<td>消費者ID</td>
				<td>価格</td>
				<td>数量</td>
				<td>小計</td>
			</tr>
		</thead>
		<tbody>
			<% for(BuyedBean bean : infoBean.getBuyedBeanArray()) { %>
			<tr>
				<td><%=bean.getItemId() %></td>
				<td><%=bean.getConsumerId() %></td>
				<td><%=bean.getPrice() %></td>
				<td><%=bean.getQuantity() %></td>
				<td><%=bean.getPrice() * bean.getQuantity() %></td>
				<% total = total + (bean.getPrice() * bean.getQuantity()); %>
			</tr>
			<% } %>
		</tbody>
	</table>
合計<%=total %>
<%} else {%>
	売り上げ情報は存在しません。
<% } %>


</body>
</html>