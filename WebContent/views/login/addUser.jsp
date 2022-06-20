<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザ登録</title>
</head>
<body>

<%try{ %>
	<% if(session.getAttribute("addUserMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("addUserMessage") %></h3>
	<%}
	session.removeAttribute("addUserMessage");
	%>

<%} catch (Exception e) {} %>

<p>ユーザ情報登録</p>

<form action="../../addUserServlet" method="post">

	<p><label>名前</label><br>
	姓<input type="text" name="last_name">
	名<input type="text" name="first_name"></p>

	<p><label>性別</label><br>
	<input type="radio" name="sex" value="1">男&nbsp;
	<input type="radio" name="sex" value="2">女</p>

	<p><label>ID</label><br>
	<input type="text" name="id"></p>

	<p><label>password</label><br>
	<input type="password" name="password"></p>

	<p><label>選択項目</label><br>
	<input type="radio" name="select" value="consumer">購入者&nbsp;
	<input type="radio" name="select" value="producer">生産者</p>

	<p><label>生年月日</label><br>
	<select name="year" >
		<option value=""></option>
		<c:forEach var="i" begin="1920" end="2022">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>年&nbsp;
	<select name="month" >
		<option value=""></option>
		<c:forEach var="i" begin="1" end="12">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>月&nbsp;
	<select name="day" >
		<option value=""></option>
		<c:forEach var="i" begin="1" end="31">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>日&nbsp;

	<p><label>電話番号</label><br>
	<input type="text" name="phone_number"></p>

	<p><label>メールアドレス</label><br>
	<input type="text" name="mail_address"></p>

	<a href="login.jsp">
    	<button type="button">戻る</button>
	</a>
	<input type="reset" value="リセット">

	<input type="submit" value="登録">


</form>

</body>
</html>