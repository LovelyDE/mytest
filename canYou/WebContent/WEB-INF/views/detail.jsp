<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>detail.jsp</title>
</head>
<body>
	<div>
		<fieldset>
			<legend>Project List</legend>
				<table>
					<thead></thead>
					<tbody>
						<c:forEach var="e" items="${list}">
							<input type="hidden" id="projectNo" value="${e.projectNo}" name="projectNo">
							<tr>
								<td>프로젝트 명 : ${e.projectName}</td>
							</tr>
							<tr>
								<td>목표금액 : ${e.projectCost}</td>
							</tr>
							<tr>
								<td>프로젝트 시작날짜 : ${e.projectStartDate}</td>
							</tr>
							<tr>
								<td>프로젝트 마감날짜 : ${e.projectEndDate}</td>
							</tr>
							<tr>
								<td>후원자 수 : ${e.projectFundCnt}</td>
							</tr>
							<tr>
								<td>프로젝트 이미지 :</td>
								<td><img id="projectMainImage"
									src="resources/images/${e.projectMainImage }"
									style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;">
								</td>
							</tr>

							<tr>
								<td>프로젝트 스토리 : ${e.projectStory}</td>
							</tr>
							<tr>
								<td>프로젝트 등급 : ${e.projectStep}</td>
							</tr>
							<tr>
								<td>추가 후원 : <input type="text" name="donateMoney" id="donateMoney"></td>
							</tr>
							<tr>
								<td><input type="button" class="donateBtn" value="후원">
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>

					</tfoot>
				</table>
		</fieldset>
	</div>
</body>

<script>
	$(function() {
		$('.donateBtn').each(function(index, item) {
			$(this).click(function() {
				var result = confirm('후원하시겠습니까?');

				if (result) {
					var projectNo = $('#projectNo').val();
					var donateMoney = $('#donateMoney').val();
					
					console.log("projectNo : "+projectNo+"/ donateMoney : "+donateMoney);
					
					location.href ='donate?projectNo='+projectNo+'&donateMoney='+donateMoney;
				}
			});
		});
	});
</script>

</html>