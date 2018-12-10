<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style type="text/css">
#bgy {
	text-align: center;
	margin: 0 auto;
	border-spacing: 40px;
	/* border-collapse: separate; */
}

.bgy1 {
	border: 3px double black;
	padding: 0px;
	width: 280px;
	height: 260px;
}
</style>
<body>

	<c:choose>
		<c:when test="${pageContext.request.userPrincipal.name != null}">
			<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
	${principal} 
	<form action="${logoutAction}" method="post">
				<input type="submit" value="Logout" />
			</form>

		</c:when>
	</c:choose>
	
	<div id="categoryproject">
		<input type="hidden" id="categoryNo" value="${categoryNo}">
		
			<c:set var="i" value="0" />
			<c:set var="j" value="3" />
			<table  id="bgy">
			<thead>
			</thead>
			
			<tbody>
				<c:forEach var="listv" items="${list}" varStatus="status">
					<c:if test="${i%j == 0 }">
						<tr>
					</c:if>
					<td class="bgy1" >
						<div id="">
							<a href="list?num=${listv.projectNo }"><img id="projectMainImage"
								src="resources/images/${listv.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;"></a>
						</div>
						<div id="">프로젝트 명 : ${listv.projectName}</div>
						<div id="">모인 금액 : ${listv.projectCost}</div>
						<div id="">남은 날짜 : ${dateList[status.index]}</div>							
					</td>
					<c:if test="${i%j == j-1 }">
					</c:if>
					<c:set var="i" value="${i+1}" />
				</c:forEach>
				</tbody>
						<tfoot>

				<!-- 페이징 -->

				<tr>
					<td colspan="6" style="text-align: center">
						<%--Page 이전 페이지 구현 --%> <c:choose>
							<c:when test="${searchType == null}">
								<c:choose>
									<c:when test="${pageInfo.currentBlock eq 1}">
                    ◀
                           </c:when>
									<c:otherwise>
										<a
											href="categoryproject?page=
         ${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock }&categoryNo=${categoryNo}">
											◀</a>
									</c:otherwise>
								</c:choose>

								<%--Page  페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock ne pageInfo.totalBlocks}">
										<c:forEach begin="1" end="${pageInfo.pagesPerBlock}"
											varStatus="num">
                        [<a
												href="categoryproject?page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }&categoryNo=${categoryNo}">
												${(pageInfo.currentBlock- 1) * pageInfo.pagesPerBlock + num.count }</a>]
                       </c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach
											begin="${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock + 1}"
											end="${pageInfo.totalPages}" varStatus="num">
                        [<a
												href="categoryproject?page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }&categoryNo=${categoryNo}">
												${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }</a>]
                    </c:forEach>
									</c:otherwise>
								</c:choose>


								<%--Page 다음 페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock eq pageInfo.totalBlocks}">
               ▶
                </c:when>
									<c:otherwise>
										<a
											href="categoryproject?page=${pageInfo.currentBlock * pageInfo.pagesPerBlock + 1 }&categoryNo=${categoryNo}">
											▶</a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${pageInfo.currentBlock eq 1}">
                    ◀◀
                           </c:when>
									<c:otherwise>
										<a
											href="categoryproject?searchType=${searchType}&searchValue=${searchValue}&page=
         ${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock }&categoryNo=${categoryNo}">
											◀</a>
									</c:otherwise>
								</c:choose>

								<%--Page  페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock ne pageInfo.totalBlocks}">
										<c:forEach begin="1" end="${pageInfo.pagesPerBlock}"
											varStatus="num">
                        [<a
												href="categoryproject?searchType=${searchType}&searchValue=${searchValue}&page=
                        ${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }&categoryNo=${categoryNo}">
												${(pageInfo.currentBlock- 1) * pageInfo.pagesPerBlock + num.count }</a>]
                       </c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach
											begin="${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock + 1}"
											end="${pageInfo.totalPages}" varStatus="num">
                        [<a
												href="categoryproject?searchType=${searchType}&searchValue=${searchValue}&page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }&categoryNo=${categoryNo}">
												${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }</a>]
                    </c:forEach>
									</c:otherwise>
								</c:choose>


								<%--Page 다음 페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock eq pageInfo.totalBlocks}">
               ▶▶
               						 </c:when>
									<c:otherwise>
										<a
											href="categoryproject?searchType=${searchType}&searchValue=${searchValue}&page=${pageInfo.currentBlock * pageInfo.pagesPerBlock + 1 }&categoryNo=${categoryNo}">
											▶</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tfoot>
			
			
		</table>
	</div>
</body>

<script>

$(function(){
	console.log($('#categoryNo').val());
});

</script>

</html>