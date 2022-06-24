<%@page import="jp.co.aforce.beans.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jp.co.aforce.beans.ItemInfoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>販売商品検索</title>
<link rel="stylesheet" href="../../css/reset2.css" />
<link rel="stylesheet" href="../../css/style.css" />
<link rel="stylesheet" href="../../css/header-7.css" />
<link rel="stylesheet" href="../../css/form2.css" />
<link rel="stylesheet" href="../../css/button.css" />
<link rel="stylesheet" href="../../css/table2.css" />
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

<%try{ %>
	<% if(session.getAttribute("searchItemMessage").toString() != "") { %>
	<br><br><p><h3 style="color: red; font-size: 50px"><%=session.getAttribute("searchItemMessage") %></h3>
	<%}
	session.removeAttribute("searchItemMessage");
	%>

<%} catch (Exception e) {} %>

<br><p style="font-size: 40px">商品名検索</p><br>
<form action="../../searchItemServlet" method="get">
	<div class="cp_iptxt container">
		<input class="ef" type="text" name="itemWord" placeholder="">
		<label>検索</label>
		<span class="focus_line"></span>
		<input type="submit" class="btn-border" value="検索">
	</div>
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
					<div class="container">
						<button class="btn-border" name="updateItem" value="<%=bean.getItemId() %>">変更</button>
					</div>
				</form></td>
				<td><form action="../../deleteItemServlet" method="post">
					<div class="container">
						<button class="btn-border" id="deleteId" name="deleteItem" value="<%=bean.getItemId() %>" data-delete="<%=bean.getItemName() %>" onclick="return test(this)">削除</button>
					</div>
				</form></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<script type="text/javascript" src="../../js/confirm.js"></script>
<script src="../../js/header-7.js"></script>

</body>
</html>