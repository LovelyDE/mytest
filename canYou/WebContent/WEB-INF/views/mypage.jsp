<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>mypage.jsp</title>
</head>
<body>
	<fieldset>
	<legend> ���� ������ </legend>
	<legend> ���� ������ ������Ʈ </legend>
		<table border="1">
			<thead>
			<c:forEach items="${list}" var="listv">
				<tr>
					<td>������Ʈ : ${listv.projectName}
					<input id="projectNo" type="hidden" value="${listv.projectNo}">
					</td>
					<td><input type="button" class="deleteBtn" value="��� ���"></td>
				</tr>
			</c:forEach>
			</thead>
		</table>
	</fieldset>
</body>

<script>
$(function(){
	$('.deleteBtn').each(function(index, item) { 
		$(this).click(function(){
			var result = confirm('�����Ͻðڽ��ϱ�?');
			
			if(result){
				location.href='cancle?projectNo='+$('#projectNo').val();
			}
		});
	});
});
</script>

</html>