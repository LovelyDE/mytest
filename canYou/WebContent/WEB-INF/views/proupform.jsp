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
<h1>������Ʈ ���ε�</h1>
<form action="proup" method="post" enctype="multipart/form-data">
<input type="hidden" name="memberNo" value="${memberNo }">
<ul>
	<li>������ �̸� : ${memberName }</li>
	<li>������Ʈ �̸� : <input type="text" name="projectName"></li>
	<li>������Ʈ ��ǥ �̹��� : <input type="file" name="mfile"></li>
	<li>������Ʈ ī�װ� : <select name="categoryNo">
					<option value="-1">����</option>
					<c:forEach var="v" items="${category}">
						<option value="${v.categoryNo}">${v.categoryName}</option>
    				</c:forEach>
				  </select></li>
	<li>������Ʈ ���� ��¥(���� ��¥���� 10~60�� ���ķ� ���� ����) : <input type="date" name="projectEndDate"></li> <!-- �������� �ϼ��Ұ� -->
	<li>���� �߰�</li>
	<li>��ǥ �ݾ� : <input type="text" name="projectCost"></li>
	<li>������Ʈ ���丮 : <br><textarea rows="20" cols="20" name="projectStory" id="cont"></textarea></li>
	
	<li><input type="submit" value="��û"></li>
</ul>
</form>
</body>
</html>