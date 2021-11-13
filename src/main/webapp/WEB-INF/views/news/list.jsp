<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News - List</title>
</head>
<body>
	<div>
		<h3 style="color: red; background-color: yellow; text-align: center">${msg}</h3>
	</div>
	<h1>List</h1>
	<a href="${pageContext.request.contextPath}/news/add">Add News</a>
	<c:choose>
		<c:when test="${not empty datas}">
			<ul>
				<c:forEach items="${datas}" var="news">
					<li>
						<div>
							<h3>${news.title}</h3>
							<p>${news.detail}</p>
							<strong>${news.author}</strong>
						</div>
						<a href="${pageContext.request.contextPath}/news/detail/${news.id}">
							<img alt="${pageContext.request.contextPath}/${news.picture}" src="${pageContext.request.contextPath}/uploads/123.jpg">
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<div>
				<p>Data empty</p>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>