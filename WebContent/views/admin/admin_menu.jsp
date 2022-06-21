<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ！</title>
</head>
<body>

おかえりなさい、${sessionScope.userName}さん！

<p>
<a href="../../adminSearchItemServlet">
    <button type="button">商品検索</button>
</a>
<a href="../../adminSearchUserServlet">
    <button type="button">ユーザ検索</button>
</a>
</p>

</body>
</html>