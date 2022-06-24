<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="../../css/reset.min.css" />
<link rel="stylesheet" href="../../css/style.css" />
<link rel="stylesheet" href="../../css/header-7.css" />
<link rel="stylesheet" href="../../css/sample2.css">
</head>
<body>

<jsp:include page="header.html" ></jsp:include>

<%try{ %>
	<% if(session.getAttribute("loginMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("loginMessage") %></h3>
	<%}
	session.removeAttribute("loginMessage");
	%>

<%} catch (Exception e) {} %>


	<div class="form-wrapper">
		<h1>Sign In</h1>
		<form action="../../loginServlet" method="post">

			<div class="form-item">
				<label for="id"></label>
				<input type="text" name="id" placeholder="ID">
			</div>
			<div class="form-item">
				<label for="password"></label>
				<input type="password" name="password" placeholder="Password">
			</div>
			<div class="button-panel">
      			<input type="submit" class="button" title="Sign In" value="Sign In"></input>
    		</div>
    		<div class="button-panel">
      			<input type="reset" class="button" title="Reset" value="Reset"></input>
    		</div>
    	</form>
		<div class="form-footer">
			<p><a href="addUser.jsp">新規ユーザ登録</a></p>
		</div>
	</div>


<%session.invalidate(); %>
<script src="../../js/header-7.js"></script>
</body>
</html>

