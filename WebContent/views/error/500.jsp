<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サーバでエラーが発生しました。</title>
<link rel="stylesheet" href="../../css/reset2.css" />
<link rel="stylesheet" href="../../css/style.css" />
<link rel="stylesheet" href="../../css/header-7.css" />
<link rel="stylesheet" href="../../css/error.css" />
</head>
<body>
<%@page import="jp.co.aforce.beans.RoleBean"%>
<%
RoleBean roleBean = (RoleBean) session.getAttribute("userInfo");
if(roleBean == null || !roleBean.getRole().equals("admin")) { %>
	<jsp:include page="../admin/header.html" ></jsp:include>
<%
} else if(roleBean == null || !roleBean.getRole().equals("consumer")) {%>
	<jsp:include page="../consumer/header.html" ></jsp:include>
<%
} else if(roleBean == null || !roleBean.getRole().equals("producer")) {%>
	<jsp:include page="../producer/header.html" ></jsp:include>
<%
} else {%>
	<jsp:include page="../login/header.html" ></jsp:include>
<%
}
%>

<p class="errorNumber">500</p>

<p class="errorTitle">Internal Server Error</p>

<p class="errorMessage">サーバでエラーが発生しました。</p>

<script src="../../js/header-7.js"></script>
</body>
</html>