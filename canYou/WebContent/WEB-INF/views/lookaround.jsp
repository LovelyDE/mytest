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

<h1><a href="AllList">모든 프로젝트</a></h1>
<h2>카테고리 별 보기</h2>
<table>
<c:forEach var="e" items="${list}" varStatus="idx">
      <tr>
         <td>
         <a href="categoryproject?categoryNo=${idx.index+1}">▶${e.categoryName}</a>
         </td>
      </tr>
   </c:forEach>
  </table>
</body>
</html>