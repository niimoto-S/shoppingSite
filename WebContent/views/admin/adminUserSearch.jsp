<%@page import="jp.co.aforce.beans.UserBeanEx"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jp.co.aforce.beans.UserInfoBeanEx" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ検索</title>
<link rel="stylesheet" href="../../css/reset2.css" />
<link rel="stylesheet" href="../../css/style.css" />
<link rel="stylesheet" href="../../css/header-7.css" />
<link rel="stylesheet" href="../../css/button.css" />
<link rel="stylesheet" href="../../css/table3.css" />
<link rel="stylesheet" href="../../css/form2.css" />
</head>
<body>
<jsp:include page="header.html" ></jsp:include>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("admin")) {
	response.sendRedirect("/ShoppingSite/views/login/login.jsp");
}
%>

<%try{ %>
	<% if(session.getAttribute("adminSearchUserMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("adminSearchUserMessage") %></h3>
	<%}
	session.removeAttribute("adminSearchUserMessage");
	%>

<%} catch (Exception e) {} %>

<br><p style="font-size: 40px">ユーザID検索</p><br>
	<form action="../../adminSearchUserServlet" method="get">

		<div class="cp_iptxt container">
			<input class="ef" type="text" name="userId" placeholder="">
			<label>ユーザID</label>
			<span class="focus_line"></span>
		<input type="submit" class="btn-border" value="検索">
		</div>
	</form>
<% UserInfoBeanEx infoBeanEx = (UserInfoBeanEx) session.getAttribute("userInfoBean"); %>
<% if(infoBeanEx.getArraySize() > 0) {%>

	<table border="1">
		<thead>
			<tr>
				<td>ユーザID</td>
				<td>姓</td>
				<td>名</td>
				<td>性別</td>
				<td>選択</td>
				<td>生年月日_年</td>
				<td>生年月日_月</td>
				<td>生年月日_日</td>
				<td>電話番号</td>
				<td>メールアドレス</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
		<% for(UserBeanEx beanEx : infoBeanEx.getUserBeanExArray()) { %>
			<tr>
				<td><%=beanEx.getId() %></td>
				<td><%=beanEx.getLastName() %></td>
				<td><%=beanEx.getFirstName() %></td>
				<td><% if(Integer.parseInt(beanEx.getSex()) == 1) { %>男<%} else{ %>女<%} %></td>
				<td><% if(beanEx.getRole().equals("consumer")) {%>消費者<%}else{%>生産者<%} %></td>
				<td><%=beanEx.getYear() %></td>
				<td><%=beanEx.getMonth() %></td>
				<td><%=beanEx.getDay() %></td>
				<td><%=beanEx.getPhoneNumber() %></td>
				<td><%=beanEx.getMailAddress() %></td>
				<td>
				<form action="../../adminDeleteUserServlet" method="post">
					<button id="deleteId" name="deleteUser" value="<%=beanEx.getId() %>" data-delete="<%=beanEx.getId() %>" onclick="return test2(this)">削除</button>
				</form>
				</td>
			</tr>
		<%} %>
		</tbody>
	</table>

<%} else { %>検索結果がありません。<%} %>
<script type="text/javascript" src="../../js/confirm.js"></script>
<script src="../../js/header-7.js"></script>
</body>
</html>