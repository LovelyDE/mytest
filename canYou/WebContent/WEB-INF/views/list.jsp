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
							<td>������Ʈ ��ȣ : <input type="text" name="projectNo"
								value="${e.projectNo}"></td>
						</tr>
						<tr>
							<td>ȸ����ȣ : <input type="text" name="memberNo"
								value="${e.memberNo}"></td>
						<tr>
							<td>������Ʈ �� : <input type="text" name="projectName"
								value="${e.projectName}"></td>
						</tr>
						<tr>
							<td>��ǥ�ݾ� : <input type="text" name="projectCost"
								value="${e.projectCost}"></td>
						</tr>
						<tr>
							<td>������Ʈ ���۳�¥ : <input type="text" name="projectStartDate"
								value="${e.projectStartDate}"></td>
						</tr>
						<tr>
							<td>������Ʈ ������¥ : <input type="text" name="projectEndDate"
								value="${e.projectEndDate}"></td>
						</tr>
						<tr>
							<td>�Ŀ��� �� : <input type="text" name="projectFundCnt"
								value="${e.projectFundCnt}"></td>
						</tr>
						<tr>
							<td>������Ʈ �̹��� :</td>
							<td><img id="projectMainImage"
								src="resources/images/${e.projectMainImage }"
								style="margin: 60px 10px 10px 10px; width: 260px; height: 280px;">
							</td>
							</tr>
						<tr>
							<td>�̹��� ���ε� : <input name="mfile" id="mfile" type="file"></td>
						</tr>
						<tr>
							<td>������Ʈ ���丮 : <input type="text" name="projectStory"
								value="${e.projectStory}"></td>
						</tr>
						<tr>
							<td>������Ʈ ��� : <input type="text" name="projectStept"
								value="${e.projectStep}"></td>
						</tr>
						<tr>
							<td>���࿩�� : <input type="text" name="projectStatus"
								value="${e.projectStatus}"></td>
						</tr>
						<tr>
							<td>ī�װ� ��ȣ : <input type="text" name="categoryNo"
								value="${e.categoryNo}"></td>
						</tr>


					</c:forEach>
					
				</tbody>
				</thead>
				<tfoot>
					<tr>
						<td colspan="14">
						<input type="submit" id="wBtn" value="����">
					</td>
					</tr>
				</tfoot>
			</table>
		</fieldset>
	</div>

</body>

</html>
