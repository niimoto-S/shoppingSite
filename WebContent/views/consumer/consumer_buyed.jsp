<%@page import="jp.co.aforce.beans.BuyedBeanEx"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jp.co.aforce.beans.BuyedInfoBeanEx" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
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
	<% if(session.getAttribute("buyedMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("buyedMessage") %></h3>
	<%}
	session.removeAttribute("buyedMessage");
	%>

<%} catch (Exception e) {} %>

購入履歴

<p><a href="consumer_menu.jsp">
    	<button type="button">戻る</button>
</a></p>
<% BuyedInfoBeanEx infoBeanEx = (BuyedInfoBeanEx) session.getAttribute("buyedInfoBeanEx"); %>
<% if(infoBeanEx.getArraySize() > 0) { %>

	<table border="1">
		<thead>
			<tr>
				<td>商品名</td>
				<td>産地</td>
				<td>数量</td>
				<td>単位</td>
				<td>価格</td>
				<td>説明</td>
				<td>画像</td>
				<td>小計</td>
			</tr>
		</thead>
		<tbody>
		<% for(BuyedBeanEx beanEx : infoBeanEx.getBuyedBeanExArray()) { %>
			<tr>
				<td><%=beanEx.getItemName() %></td>
				<td><%=beanEx.getOrigin() %></td>
				<td><%=beanEx.getQuantity() %></td>
				<td><%=beanEx.getUnit() %></td>
				<td><%=beanEx.getPrice() %></td>
				<td><%=beanEx.getExplanation() %></td>
				<td><img src="../../img/<%=beanEx.getImage() %>" width="250px" height="250px"></td>
				<td><%=beanEx.getQuantity() * beanEx.getPrice() %>
				<%total = total + (beanEx.getQuantity() * beanEx.getPrice()); %>
			</tr>
		<% } %>
		</tbody>
	</table>
	購入合計金額:<%=total %>

<%}else{ %>
	購入履歴がありません。
<%} %>

</body>
</html>