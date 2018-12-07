<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>AllList.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div>
		<fieldset>
			<table>

				<c:forEach var="e" items="${list}">

					<tr>
						<td>������Ʈ �̹��� :</td>
							<td><a href="list?num=${e.projectNo }"><img id="projectMainImage"
								src="resources/images/${e.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;"></a>
							</td>
						<td>������Ʈ �� : ${e.projectName}</td>
					
						<td>���� ���αݾ� : ${e.projectCurCost}</td>
					</tr>

				</c:forEach>
			</table>
		</fieldset>
	</div>

</body>

</html>
