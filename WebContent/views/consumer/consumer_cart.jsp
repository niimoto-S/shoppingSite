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
</head>
<body>

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
	<p><h3 style="color: red"><%=session.getAttribute("cartMessage") %></h3>
	<%}
	session.removeAttribute("cartMessage");
	%>

<%} catch (Exception e) {} %>

	カート

<p><a href="consumer_menu.jsp">
    	<button type="button">戻る</button>
</a></p>
<% MyBasketInfoBean infoBean = (MyBasketInfoBean) session.getAttribute("myBasket"); %>
<% if(infoBean.getArraySize() > 0){ %>
マイカート<br>
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
				<select name="quantity" id="quantity" form="test<%=bean.getItemId() %>">
					<option value="<%=bean.getQuantity() %>"><%=bean.getQuantity() %></option>
					<c:forEach var="i" begin="1" end="100">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<%=bean.getUnit() %>
				</td>
				<td><%=bean.getPrice() %>円</td>
				<td><%=bean.getExplanation() %></td>
				<td><img alt="画像" src="../../img/<%=bean.getImage() %>" width="250px" height="250px"></td>
				<td><%=bean.getQuantity() * bean.getPrice() %>円</td>
				<% total = total + (bean.getQuantity() * bean.getPrice()); %>
				<td>
				<form action="../../updateCartServlet" method="post" id="test<%=bean.getItemId() %>">
					<input type="hidden" name="updateItem" value="<%=bean.getItemId() %>">
					<input type="submit" value="変更" data-update="<%=bean.getItemName() %>" onclick="return test1(this)">
				</form>
				</td>
				<td>
				<form action="../../deleteCartServlet" method="post">
					<button id="deleteId" name="deleteCart" value="<%=bean.getItemId() %>" data-delete="<%=bean.getItemName() %>" onclick="return test(this)">削除</button>
				</form>
				</td>
			</tr>

		<% } %>
	</tbody>
</table>

合計<%=total %>円
<form action="../../buyedServlet" method="post">
	<input type="submit" value="購入">
</form>

<% } else { %>
	カートには何も入っていません。
	<a href="../../searchAllServlet">
    <button type="button">商品</button>
	</a>
<% } %>
<script type="text/javascript" src="../../js/confirm.js"></script>
</body>
</html>