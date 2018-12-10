<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>myProject.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="resources/css/mypage.css" type="text/css" />
</head>
<body>
	<div>
		<fieldset>
			<legend>내가만든 프로젝트</legend>


			<c:set var="i" value="0" />
			<c:set var="j" value="3" />
			<table>
				<c:forEach  var="e" items="${list}">
					<c:if test="${i%j == 0 }">
						<tr>
					</c:if>
					<td style="border: 2px solid black; padding: 10px;">
						<div id="">
							<img id="projectMainImage"
								src="resources/images/${e.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;">
						</div>
						<div id="">프로젝트 명 : ${e.projectName}</div>
						<div id="">프로젝트 등급 : ${e.projectStep}</div>
						<div id="">진행여부 : ${e.projectStatus}</div>
						<div id="">목표금액 : ${e.projectCost}</div>
						<div id="">프로젝트 시작날짜 : ${e.projectStartDate}</div>
						<div id="">프로젝트 마감날짜 : ${e.projectEndDate}</div>
						<div id="">후원자 수 : ${e.projectFundCnt}</div>
								
					</td>
					<c:if test="${i%j == j-1 }">
						</tr>
					</c:if>
					<c:set var="i" value="${i+1 }" />
				</c:forEach>
			</table>





		</fieldset>
	</div>
</body>
</html>

