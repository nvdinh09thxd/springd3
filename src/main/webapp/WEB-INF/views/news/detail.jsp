<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News - Detail</title>
</head>
<body>
	<h1>${news.title}</h1>
	<p>
		<span>Create by: <fmt:formatDate value="${news.createBy}" pattern="yyyy-MM-dd HH:mm" /></span>
	</p>
	<p>${news.detail}</p>
	<a href="javascript:window.history.back()">Back List</a>
</body>
</html>