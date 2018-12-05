<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>list.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div>
		<fieldset>
			<legend>Project List</legend>
			<table>
				<thead>
				<tbody>
					<c:forEach var="e" items="${list}">
						<tr>
							<td>프로젝트 번호 : <input type="text" name="projectNo"
								value="${e.projectNo}"></td>
						</tr>
						<tr>
							<td>회원번호 : <input type="text" name="memberNo"
								value="${e.memberNo}"></td>
						<tr>
							<td>프로젝트 명 : <input type="text" name="projectName"
								value="${e.projectName}"></td>
						</tr>
						<tr>
							<td>목표금액 : <input type="text" name="projectCost"
								value="${e.projectCost}"></td>
						</tr>
						<tr>
							<td>프로젝트 시작날짜 : <input type="text" name="projectStartDate"
								value="${e.projectStartDate}"></td>
						</tr>
						<tr>
							<td>프로젝트 마감날짜 : <input type="text" name="projectEndDate"
								value="${e.projectEndDate}"></td>
						</tr>
						<tr>
							<td>후원자 수 : <input type="text" name="projectFundCnt"
								value="${e.projectFundCnt}"></td>
						</tr>
						<tr>
							<td>프로젝트 이미지 :</td>
							<td><img id="projectMainImage"
								src="resources/images/${e.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;">
							</td>
							</tr>
						<tr>
							<td>이미지 업로드 : <input name="mfile" id="mfile" type="file"></td>
						</tr>
						<tr>
							<td>프로젝트 스토리 : <input type="text" name="projectStory"
								value="${e.projectStory}"></td>
						</tr>
						<tr>
							<td>프로젝트 등급 : <input type="text" name="projectStept"
								value="${e.projectStep}"></td>
						</tr>
						<tr>
							<td>진행여부 : <input type="text" name="projectStatus"
								value="${e.projectStatus}"></td>
						</tr>
						<tr>
							<td>카테고리 번호 : <input type="text" name="categoryNo"
								value="${e.categoryNo}"></td>
						</tr>


					</c:forEach>
					
				</tbody>
				</thead>
				<tfoot>
					<tr>
						<td colspan="14">
						<input type="submit" id="wBtn" value="수정">
					</td>
					</tr>
				</tfoot>
			</table>
		</fieldset>
	</div>

</body>

</html>
