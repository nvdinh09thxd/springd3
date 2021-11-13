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
	<h1>${news.getTitle()}</h1>
	<p>
		<span>Create by: <fmt:formatDate value="${news.getCreateBy()}" pattern="yyyy-MM-dd HH:mm" /></span>
	</p>
	<p>${news.getDetail()}</p>
	<img width="150px" height="150px" alt="" src="${pageContext.request.contextPath}/resources/uploads/${news.getPicture()}">
	<a href="javascript:window.history.back()">Back List</a>
</body>
</html>