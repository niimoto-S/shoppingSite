<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>販売商品追加</title>
<link rel="stylesheet" href="css/reset2.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/header-7.css" />
<link rel="stylesheet" href="css/form.css" />
<link rel="stylesheet" href="css/select.css" />
<link rel="stylesheet" href="css/button.css" />
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
	<% if(session.getAttribute("addItemMessage").toString() != "") { %>
	<br><br><p><h3 style="color: red; font-size: 50px"><%=session.getAttribute("addItemMessage") %></h3><br>
	<%}
	session.removeAttribute("addItemMessage");
	%>

<%} catch (Exception e) {} %>

<br><p style="font-size: 40px">販売商品追加</p><br>
<div class="form-wrapper">
<form action="addItemServlet" method="post" enctype="multipart/form-data">

	<p style="font-size: 20px">商品名</p>
	<div class="cp_iptxt">
			<input class="ef" type="text" name="item_name" placeholder="">
			<label>商品名</label>
			<span class="focus_line"></span>
		</div>

	<p style="font-size: 20px">産地</p>
	<div class="cp_iptxt">
			<input class="ef" type="text" name="origin" placeholder="">
			<label>産地</label>
			<span class="focus_line"></span>
		</div>

	<p style="font-size: 20px">単位</p>
	<div class="cp_ipselect">
		<select name="unit" class="cp_sl06">
			<option value="" hidden="" disabled selected></option>
			<option value="kg">kg</option>
			<option value="g">g</option>
			<option value="ml">ml</option>
			<option value="L">L</option>
			<option value="個">個</option>
			<option value="袋">袋</option>
			<option value="箱">箱</option>
			<option value="匹">匹</option>
		</select>
		<span class="cp_sl06_highlight"></span>
		<span class="cp_sl06_selectbar"></span>
		<label class="cp_sl06_selectlabel"></label>
	</div>

	<p style="font-size: 20px">価格</p>
	<div class="cp_iptxt">
			<input class="ef" type="number" min="0" name="price" placeholder="">
			<label>円</label>
			<span class="focus_line"></span>
		</div>

	<p><label>説明(1000文字以内)</label><br>
	<textarea name="explanation" rows="6" cols="120" maxlength="1000"></textarea></p>

	<p><label>画像</label><br>
	<input type="file" name="image" accept="image/png, image/jpeg" onchange="previewImage(this);">
	</p>
	<img id="preview" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" height="250px" width="250">
	<br><br>
	<div class="container">
		<a href="searchItemServlet" class="btn-border">戻る</a>
		<input type="reset" class="btn-border" value="リセット">
		<input type="submit" class="btn-border" value="追加">
	</div>

</form>
</div>
<script type="text/javascript" src="js/image.js"></script>
<script src="js/header-7.js"></script>
</body>
</html>