<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>販売商品追加</title>
</head>
<body>

<%try{ %>
	<% if(session.getAttribute("updateItemMessage").toString() != "") { %>
	<p><h3 style="color: red"><%=session.getAttribute("updateItemMessage") %></h3>
	<%}
	session.removeAttribute("updateItemMessage");
	%>

<%} catch (Exception e) {} %>

<p>販売商品追加</p>

<form action="../../updateItem2Servlet" method="post" enctype="multipart/form-data">

	<p><label>商品名</label><br>
	<input type="text" name="item_name" value="${sessionScope.updateItemBean.itemName }">
	</p>

	<p><label>産地</label><br>
	<input type="text" name="origin" value="${sessionScope.updateItemBean.origin }"></p>

	<p><label>単位</label><br>
	<select name="unit">
		<option value="${sessionScope.updateItemBean.unit }">${sessionScope.updateItemBean.unit }</option>
		<option value="kg">kg</option>
		<option value="g">g</option>
		<option value="ml">ml</option>
		<option value="L">L</option>
		<option value="個">個</option>
		<option value="袋">袋</option>
		<option value="箱">箱</option>
		<option value="匹">匹</option>
	</select></p>

	<p><label>価格</label><br>
	<input type="number" name="price" value="${sessionScope.updateItemBean.price }"></p>

	<p><label>説明(1000文字以内)</label><br>
	<textarea name="explanation" rows="6" cols="120" maxlength="1000" >${sessionScope.updateItemBean.explanation }</textarea></p>

	<p><label>画像</label><br>
	<input type="file" name="image" accept="image/png, image/jpeg" onchange="previewImage(this);" >
	</p>
	<p>
	<img alt="画像" src="../../img/${sessionScope.updateItemBean.imageName }" height="250px" width="250">変更後→
	<img id="preview" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" height="250px" width="250">
	</p>
	<a href="producer_menu.jsp">
    	<button type="button">戻る</button>
	</a>
	<input type="reset" value="リセット">

	<input type="submit" value="登録">

</form>

<script type="text/javascript" src="../../js/image.js"></script>

</body>
</html>