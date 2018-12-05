<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>canYou</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.4.7/full/ckeditor.js"></script>
<script>
$(document).ready(function(){
	var d = new Date();
	
	CKEDITOR.replace('cont', {
		height: '500px' ,
		filebrowserImageUploadUrl: 'imageUpload'
	})
});
</script>
</head>
<body>
<h1>프로젝트 업로드</h1>
<form action="proup" method="post" enctype="multipart/form-data">
<input type="hidden" name="memberNo" value="${memberNo }">
<ul>
	<li>진행자 이름 : ${memberName }</li>
	<li>프로젝트 이름 : <input type="text" name="projectName"></li>
	<li>프로젝트 대표 이미지 : <input type="file" name="mfile"></li>
	<li>프로젝트 카테고리 : <select name="categoryNo">
					<option value="-1">선택</option>
					<c:forEach var="v" items="${category}">
						<option value="${v.categoryNo}">${v.categoryName}</option>
    				</c:forEach>
				  </select></li>
	<li>프로젝트 종료 날짜(현재 날짜에서 10~60일 이후로 선택 가능) : <input type="date" name="projectEndDate"></li> <!-- 제약조건 완성할것 -->
	<li>선물 추가</li>
	<li>목표 금액 : <input type="text" name="projectCost"></li>
	<li>프로젝트 스토리 : <br><textarea rows="20" cols="20" name="projectStory" id="cont"></textarea></li>
	
	<li><input type="submit" value="신청"></li>
</ul>
</form>
</body>
</html>