<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>카테고리 별 보기</h2>
	<c:forEach var="e" items="${list}">
		<tr>
			<td><h3><a href="list">▶${e.categoryName}</a></h3></td>
		</tr>
	</c:forEach>
</body>
</html>