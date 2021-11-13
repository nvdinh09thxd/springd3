<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News - Add</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/news/add-news" enctype="multipart/form-data">
		<label>Title:</label> <input type="text" name="title" /> <br /> <br />
		<label>Author:</label> <input type="text" name="author" /> <br /> <br />
		<label>Detail:</label> <input type="text" name="detail" /> <br /> <br />
		<label>Picture:</label> <input type="file" name="picture" /> <br /> <br />
		<label>Status:</label> <input type="text" name="status" /> <br /> <br />
		<input type="submit" value="Submit" />
		<input type="reset" value="Reset" />
	</form>
</body>
</html>