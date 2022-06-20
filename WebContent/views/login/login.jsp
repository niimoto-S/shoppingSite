<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>



<%try{ %>
	<% if(session.getAttribute("loginMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("loginMessage") %></h3>
	<%}
	session.removeAttribute("loginMessage");
	%>

<%} catch (Exception e) {} %>

	<p>ログイン情報入力</p>

	<form action="../../loginServlet" method="post">
		<p><label>ID</label><br><input type="text" name="id"></p>
		<p><label>PASSWORD</label><br><input type="password" name="password"><p>
		<input type="reset" value="リセット">
		<input type="submit" value="ログイン">
		<a href="addUser.jsp">
    	<button type="button">新規ユーザ登録</button>
		</a>
	</form>
<%session.invalidate(); %>
</body>
</html>
