<%@page import="jp.co.aforce.beans.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jp.co.aforce.beans.ItemInfoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>

<%try{ %>
	<% if(session.getAttribute("searchItemMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("searchItemMessage") %></h3>
	<%}
	session.removeAttribute("searchItemMessage");
	%>

<%} catch (Exception e) {} %>

	商品検索

<p><a href="consumer_menu.jsp">
    	<button type="button">戻る</button>
</a></p>

商品名検索<br>
<form action="../../searchAllServlet" method="get">
	<input type="text" name="itemName">
	<input type="submit" value="検索">
</form>

	<table border="1">
		<thead>
			<tr>
				<td>商品名</td>
				<td>産地</td>
				<td>数量(単位)</td>
				<td>価格</td>
				<td>説明</td>
				<td>画像</td>
				<td>カート</td>
			</tr>
		</thead>
		<tbody>
			<% ItemInfoBean infoBean = (ItemInfoBean) session.getAttribute("itemInfoBean"); %>
			<% for(ItemBean bean : infoBean.getItemBeanArray()) { %>
			<tr>
				<td><%=bean.getItemName() %></td>
				<td><%=bean.getOrigin() %></td>
				<td>
				<select name="quantity" id="quantity" form="test<%=bean.getItemId() %>">
					<option value=""></option>
					<c:forEach var="i" begin="1" end="100">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<%=bean.getUnit() %></td>
				<td><%=bean.getPrice() %>円</td>
				<td><%=bean.getExplanation() %></td>
				<td><img alt="<%=bean.getImageName() %>" src="../../img/<%=bean.getImageName() %>" height="250px" width="250px"></td>
				<td>
				<form action="../../addCartServlet" method="post" id="test<%=bean.getItemId() %>">
					<input type="hidden" name="addItem" value="<%=bean.getItemId() %>">
					<input type="submit" value="追加">
				</form></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<script type="text/javascript" src="../../js/confirm.js"></script>
<script type="text/javascript" src="../../js/hidden.js"></script>

</body>
</html>