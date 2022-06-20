<%@page import="jp.co.aforce.beans.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jp.co.aforce.beans.ItemInfoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>販売商品検索</title>
</head>
<body>

<%try{ %>
	<% if(session.getAttribute("searchItemMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("searchItemMessage") %></h3>
	<%}
	session.removeAttribute("searchItemMessage");
	%>

<%} catch (Exception e) {} %>

	販売商品検索

<p><a href="producer_menu.jsp">
    	<button type="button">戻る</button>
</a></p>

商品名検索<br>
<form action="../../searchItemServlet" method="get">
	<input type="text" name="itemWord">
	<input type="submit" value="検索">
</form>

	<table border="1">
		<thead>
			<tr>
				<td>商品名</td>
				<td>産地</td>
				<td>単位</td>
				<td>価格</td>
				<td>説明</td>
				<td>画像</td>
				<td colspan="2"></td>
			</tr>
		</thead>
		<tbody>
			<% ItemInfoBean infoBean = (ItemInfoBean) session.getAttribute("itemInfoBean"); %>
			<% for(ItemBean bean : infoBean.getItemBeanArray()) { %>
			<tr>
				<td><%=bean.getItemName() %></td>
				<td><%=bean.getOrigin() %></td>
				<td><%=bean.getUnit() %></td>
				<td><%=bean.getPrice() %></td>
				<td><%=bean.getExplanation() %></td>
				<td><img alt="<%=bean.getImageName() %>" src="../../img/<%=bean.getImageName() %>" height="250px" width="250px"></td>
				<td><form action="../../updateItemServlet" method="post">
					<button name="updateItem" value="<%=bean.getItemId() %>">変更</button>
				</form></td>
				<td><form action="../../deleteItemServlet" method="post">
					<button id="deleteId" name="deleteItem" value="<%=bean.getItemId() %>" data-delete="<%=bean.getItemName() %>" onclick="return test(this)">削除</button>
				</form></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<script type="text/javascript" src="../../js/confirm.js"></script>

</body>
</html>