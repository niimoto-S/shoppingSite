<%@page import="jp.co.aforce.beans.MyBasketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jp.co.aforce.beans.MyBasketInfoBean" %>
<%  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" href="css/reset2.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
<link rel="stylesheet" href="css/button.css" />
<link rel="stylesheet" href="css/table2.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("consumer")) {
	response.sendRedirect("/ShoppingSite/views/login/login.jsp");

}
%>

<% int total = 0; %>
<%try{ %>
	<% if(session.getAttribute("cartMessage").toString() != "") { %>
	<br><br><p><h3 style="color: red; font-size: 50px"><%=session.getAttribute("cartMessage") %></h3>
	<%}
	session.removeAttribute("cartMessage");
	%>

<%} catch (Exception e) {} %>
<br><p style="font-size: 40px">マイカート</p><br>
<% MyBasketInfoBean infoBean = (MyBasketInfoBean) session.getAttribute("myBasket"); %>
<% if(infoBean.getArraySize() > 0){ %>

<table border="1">
	<thead>
		<tr>
			<td>商品名</td>
			<td>産地</td>
			<td>数量(単位)</td>
			<td>価格</td>
			<td>説明</td>
			<td>画像</td>
			<td>小計</td>
			<td colspan="2"></td>
		</tr>
	</thead>
	<tbody>
		<% for(MyBasketBean bean : infoBean.getMyBasketBeanArray()) { %>

			<tr>
				<td><%=bean.getItemName() %></td>
				<td><%=bean.getOrigin() %></td>
				<td>
				<select name="quantity" id="quantity" form="test<%=bean.getId() %>">
					<option value="<%=bean.getQuantity() %>"><%=bean.getQuantity() %></option>
					<c:forEach var="i" begin="1" end="100">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<%=bean.getUnit() %>
				</td>
				<td><%=bean.getPrice() %>円</td>
				<td><%=bean.getExplanation() %></td>
				<td><img alt="画像" src="img/<%=bean.getImage() %>" width="250px" height="250px"></td>
				<td><%=bean.getQuantity() * bean.getPrice() %>円</td>
				<% total = total + (bean.getQuantity() * bean.getPrice()); %>
				<td>
				<form action="updateCartServlet" method="post" id="test<%=bean.getId() %>">
					<input type="hidden" name="updateItem" value="<%=bean.getId() %>">
					<div class="container">
					<input class="btn-border" type="submit" value="変更" data-update="<%=bean.getItemName() %>" onclick="return test1(this)">
					</div>
				</form>
				</td>
				<td>
				<form action="deleteCartServlet" method="post">
					<div class="container">
						<button class="btn-border" id="deleteId" name="deleteCart" value="<%=bean.getId() %>" data-delete="<%=bean.getItemName() %>" onclick="return test(this)">削除</button>
					</div>
				</form>
				</td>
			</tr>

		<% } %>
	</tbody>
</table>

<p class="total">合計:<%=total %>円</p>
<form action="buyedServlet" method="post">
	<div class="container" style="text-align: right;">
		<input type="submit" class="btn-border" style="margin-right: 100px; margin-bottom: 100px;" value="購入" onclick="return test3()">
	</div>
</form>

<% } else { %>
	カートには何も入っていません。
	<div class="container">
	<a href="searchAllServlet">
		<input type="button" class="btn-border" value="商品">
	</a>
	</div>
<% } %>
<script type="text/javascript" src="js/confirm.js"></script>
<script src="js/header-7.js"></script>
</body>
</html>