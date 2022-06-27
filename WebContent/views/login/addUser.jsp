<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザ登録</title>
<link rel="stylesheet" href="css/reset.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
<link rel="stylesheet" href="css/form.css" />
<link rel="stylesheet" href="css/radio.css" />
<link rel="stylesheet" href="css/select.css" />
<link rel="stylesheet" href="css/button.css" />
</head>
<body>

<jsp:include page="header.html" ></jsp:include>

<%try{ %>
	<% if(session.getAttribute("addUserMessage").toString() != "") { %>
	<br><br><p><h3 style="color: red; font-size: 50px"><%=session.getAttribute("addUserMessage") %></h3><br>
	<%}
	session.removeAttribute("addUserMessage");
	%>

<%} catch (Exception e) {} %>


<br><p style="font-size: 40px">ユーザ情報登録</p><br>
<div class="form-wrapper">
<form action="addUserServlet" method="post">
		<p style="font-size: 20px">名前</p>
		<div class="cp_iptxt">
			<input class="ef" type="text" name="last_name" placeholder="">
			<label>姓</label>
			<span class="focus_line"></span>
		</div>

		<div class="cp_iptxt">
			<input class="ef" type="text" name="first_name" placeholder="">
			<label>名</label>
			<span class="focus_line"></span>
		</div>

		<p style="font-size: 20px">性別</p>
		<div class="cp_ipradio">
			<input type="radio" name="sex" id="a_rb1" value="1" />
			<label for="a_rb1">男性</label>
			<input type="radio" name="sex" id="a_rb2" value="2"/>
			<label for="a_rb2">女性</label>
		</div>

		<p style="font-size: 20px">ID</p>
		<div class="cp_iptxt">
			<input class="ef" type="text" name="id" placeholder="">
			<label>id</label>
			<span class="focus_line"></span>
		</div>

		<p style="font-size: 20px">Password</p>
		<div class="cp_iptxt">
			<input class="ef" type="password" name="password" placeholder="">
			<label>Password</label>
			<span class="focus_line"></span>
		</div>

		<p style="font-size: 20px">選択項目</p>
		<div class="cp_ipradio">
			<input type="radio" name="select" id="a_rb3" value="consumer"/>
			<label for="a_rb3">購入者</label>
			<input type="radio" name="select" id="a_rb4" value="producer"/>
			<label for="a_rb4">生産者</label>
		</div>

		<p style="font-size: 20px">生年月日</p>
		<div class="cp_ipselect">
			<select name="year" class="cp_sl06">
				<option value="" hidden="" disabled selected></option>
				<c:forEach var="i" begin="1920" end="2022">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<span class="cp_sl06_highlight"></span>
			<span class="cp_sl06_selectbar"></span>
			<label class="cp_sl06_selectlabel">年</label>
		</div>
		<div class="cp_ipselect">
			<select name="month" class="cp_sl06">
				<option value="" hidden="" disabled selected></option>
				<c:forEach var="i" begin="1" end="12">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<span class="cp_sl06_highlight"></span>
			<span class="cp_sl06_selectbar"></span>
			<label class="cp_sl06_selectlabel">月</label>
		</div>
		<div class="cp_ipselect">
			<select name="day" class="cp_sl06">
				<option value="" hidden="" disabled selected></option>
				<c:forEach var="i" begin="1" end="31">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<span class="cp_sl06_highlight"></span>
			<span class="cp_sl06_selectbar"></span>
			<label class="cp_sl06_selectlabel">日</label>
		</div>

		<p style="font-size: 20px">電話番号</p>
		<div class="cp_iptxt">
			<input class="ef" type="text" name="phone_number" placeholder="">
			<label>電話番号</label>
			<span class="focus_line"></span>
		</div>

		<p style="font-size: 20px">メールアドレス</p>
		<div class="cp_iptxt">
			<input class="ef" type="text" name="mail_address" placeholder="">
			<label>メールアドレス</label>
			<span class="focus_line"></span>
		</div>

	<div class="container">
		<a href="loginServlet" class="btn-border">戻る</a>
		<input type="reset" class="btn-border" value="リセット">
		<input type="submit" class="btn-border" value="登録">
	</div>


</form>
</div>
<br><br>
<script src="js/header-7.js"></script>
</body>
</html>