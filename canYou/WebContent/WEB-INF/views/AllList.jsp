<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">
<title>AllList.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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
</head>
<body>
	<div id="AllList">
		<c:set var="i" value="0" />
		<c:set var="j" value="3" />

		<table id="bgy">
			<thead>
			</thead>

			<tbody >
				<c:forEach var="e" items="${list}" varStatus="status">
					<c:if test="${i%j == 0 }">
						<tr>
					</c:if>
					<td class="bgy1">
						<div id="">
						<a href="list?num=${e.projectNo }">
							<img id="projectMainImage"
								src="resources/images/${e.projectMainImage }"
								style="margin: 0px 0px 0px 0px; width: 100%; height: 240px;">
						</a></div>
						<p id="">프로젝트 명 : ${e.projectName}</p>
						<hr>
						<p id="">모인 금액 : ${e.projectCurCost}</p>
						<p id="">남은 날짜 : ${dateList[status.index]}</p>
					</td>
					<c:if test="${i%j == j-1 }">
					</c:if>
					<c:set var="i" value="${i+1 }" />
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
											href="AllList?page=
         ${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock }">
											◀</a>
									</c:otherwise>
								</c:choose>

								<%--Page  페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock ne pageInfo.totalBlocks}">
										<c:forEach begin="1" end="${pageInfo.pagesPerBlock}"
											varStatus="num">
                        [<a
												href="AllList?page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }">
												${(pageInfo.currentBlock- 1) * pageInfo.pagesPerBlock + num.count }</a>]
                       </c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach
											begin="${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock + 1}"
											end="${pageInfo.totalPages}" varStatus="num">
                        [<a
												href="AllList?page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }">
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
											href="AllList?page=${pageInfo.currentBlock * pageInfo.pagesPerBlock + 1 }">
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
											href="AllList?searchType=${searchType}&searchValue=${searchValue}&page=
         ${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock }">
											◀</a>
									</c:otherwise>
								</c:choose>

								<%--Page  페이지 구현 --%>
								<c:choose>
									<c:when test="${pageInfo.currentBlock ne pageInfo.totalBlocks}">
										<c:forEach begin="1" end="${pageInfo.pagesPerBlock}"
											varStatus="num">
                        [<a
												href="AllList?searchType=${searchType}&searchValue=${searchValue}&page=
                        ${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }">
												${(pageInfo.currentBlock- 1) * pageInfo.pagesPerBlock + num.count }</a>]
                       </c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach
											begin="${(pageInfo.currentBlock-1)*pageInfo.pagesPerBlock + 1}"
											end="${pageInfo.totalPages}" varStatus="num">
                        [<a
												href="AllList?searchType=${searchType}&searchValue=${searchValue}&page=${(pageInfo.currentBlock - 1) * pageInfo.pagesPerBlock + num.count }">
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
											href="AllList?searchType=${searchType}&searchValue=${searchValue}&page=${pageInfo.currentBlock * pageInfo.pagesPerBlock + 1 }">
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
</html>