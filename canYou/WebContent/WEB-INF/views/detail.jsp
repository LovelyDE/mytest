<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#tv {
    border: 1px solid black;
}
</style>
<meta charset="EUC-KR">
<title>detail.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	$('.prodview #donadd').each(function(){
		$(this).click(function(e){
			//console.log($(this).prev().html());
			$('#selprod').html($(this).prev().html());
			$('#selprod').css('border', '1px pink solid');
		});
	});
}); 
</script>
</head>
<body>
<div>
		<fieldset>
			<legend>Project List</legend>
			<form action="update" method="POST" >
			<input type="hidden" id="projectNo" name="projectNo" value="${list.projectNo}">
			<table>
				<thead></thead>
				<tbody>
						<tr>
							<td>프로젝트 번호 : ${list.projectNo}</td>
						</tr>
						<tr>
							<td>회원이름 : ${member.memberName}</td>
						</tr>
						<tr>
							<td>프로젝트 명 : ${list.projectName}</td>
						</tr>
						<tr>
							<td>목표금액 : ${list.projectCost}</td>
						</tr>
						<tr>
							<td>프로젝트 시작날짜 : ${list.projectStartDate}</td>
						</tr>
						<tr>
							<td>프로젝트 마감날짜 : ${list.projectEndDate}</td>
						</tr>
						<tr>
							<td>모인 후원 금액: ${list.projectCurCost}</td>
						</tr>
						<tr>
							<td>후원자 수 : ${list.projectFundCnt}</td>
						</tr>
						<tr>
							<td>프로젝트 이미지 :</td>
							<td><img id="projectMainImage"
								src="resources/images/${list.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;">
							</td>
							</tr>

						<tr>
							<td>프로젝트 스토리 : ${list.projectStory}</td>
						</tr>
						<tr>
							<td>프로젝트 등급 : ${list.projectStep}</td>
						</tr>
						<tr>
							<td>진행여부 : ${list.projectStatus}</td>
						</tr>
						<tr>
							<td>카테고리 번호 : ${list.categoryNo}</td>
						</tr>
						<tr>
							<td>
								<c:forEach var="e2" items="${prodlist}">
									<div class="prodview" style="border:1px solid gold;">
										<div>
											<input type="hidden" name="productNo" id="productNo" value="${e2.productNo }">
											<p>${e2.productCost }원 이상 구매 시</p>
											<h2>${e2.productName }</h2>
											<p>${e2.productInfo }</p>
											<p>${e2.productCnt }개 남음</p>
										</div>
									<input id="donadd" type="button" value="이걸로 후원하기">
									</div>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>
								<h2>현재 선택중인 선물</h2>
								<div id="selprod">
									
								</div>
							</td>
						</tr>
						<tr>
							<td>추가 후원 : <input type="text" name="donateMoney" id="donateMoney"></td>
						</tr>
						<tr>
							<td><input type="button" class="donateBtn" value="후원"></td>
						</tr>
						
				</tbody>
					
				<tfoot>
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name eq member.memberId }">
					<tr>
						<td colspan="14">
						<input type="submit" id="wBtn" value="수정">
						</td>
					</tr>
					</c:when>
				</c:choose>
				</tfoot>
			</table>
			</form>
			<form method="post" action="reply1">
			<table>
				<tr>
					<td>
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal.name==null}">
							<textarea rows="3" cols="30" name="replyContent" disabled>로그인 해주세요</textarea>
						</c:when>
						<c:when test="${pageContext.request.userPrincipal.name!=null}">
							<textarea rows="3" cols="30" name="replyContent"></textarea>
						</c:when>
					</c:choose>
					</td>
					<td><input type="submit" value="작성"></td>
				</tr>
			</table>
		</form>
		</fieldset>
		<table id= "tv">
		
			<c:forEach var="s" items="${replylist}">
			
				<tr>
					<td>멤버번호 : ${s.memberNo}</td>	
					<td>내용 : ${s.replyContent}</td>
					<td>날짜 : ${s.replyDate}</td>
				</tr>
				
			</c:forEach>

		</table> 
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
					var productNo = $('#selprod #productNo').val();
					
					
					//console.log("projectNo : "+projectNo+"/ donateMoney : "+donateMoney+"/ productNo:" + productNo);
					
					location.href ='donate?projectNo='+projectNo+'&donateMoney='+donateMoney+'&productNo='+productNo;
				}
			});
		});
	});
</script>
</html>

