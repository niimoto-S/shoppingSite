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
<link rel="stylesheet" href="css/reset2.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
<link rel="stylesheet" href="css/form2.css" />
<link rel="stylesheet" href="css/button.css" />
<link rel="stylesheet" href="css/table.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("consumer")) {
	request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
}
%>

<%try{ %>
	<% if(session.getAttribute("searchItemMessage").toString() != "") { %>
	<br><br><p><h3 style="color: red; font-size: 50px"><%=session.getAttribute("searchItemMessage") %></h3><br>
	<%}
	session.removeAttribute("searchItemMessage");
	%>

<%} catch (Exception e) {} %>

<br><p style="font-size: 40px">商品名検索</p><br>
<form action="searchAllServlet" method="get">

	<div class="cp_iptxt container">
		<input class="ef" type="text" name="itemName" placeholder="">
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
				<td valign="middle"><%=bean.getItemName() %></td>
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
				<td class="exp"><%=bean.getExplanation() %></td>
				<td><img alt="<%=bean.getImageName() %>" src="img/<%=bean.getImageName() %>" height="250px" width="250px"></td>
				<td>
				<form action="addCartServlet" method="post" id="test<%=bean.getItemId() %>">
					<input type="hidden" name="addItem" value="<%=bean.getItemId() %>">

					<div class="container">
						<input type="submit" class="btn-border" value="追加">
					</div>
				</form></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<script type="text/javascript" src="js/confirm.js"></script>
<script type="text/javascript" src="js/hidden.js"></script>
<script src="js/header-7.js"></script>
</body>
</html>