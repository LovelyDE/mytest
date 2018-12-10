<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>개인정보 수정</h2>
<form action="success" method="POST">
	<p><label>Pwd : </label><input type="password" name="memberPwd"></p>
	<p><label>이름 : </label><input type="text" name="memberName"></p>
	<p><label>주소 : </label><input type="text" name="memberAddr"></p>
	<p><label>휴대폰 번호 : </label><input type="text" name="memberPhone"></p>
	<p><label>이메일 : </label><input type="text" name="memberEmail"></p>
	<p><label>나이 : </label><input type="text" name="memberAge"></p>
	<p><label>계좌 : </label><input type="text" name="memberAccount"></p>
	<!--<p><label>프로필 이미지 : </label><input name="mfile" type="file"></p>-->
	
	<p><input type="submit" value="수정하기"></p>
</form>
</body>
</html>