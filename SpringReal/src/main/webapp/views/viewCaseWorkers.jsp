<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Case Workers</title>
<script>
	function confirmDelete() {
		var status = confirm("Are you sure, you want to delete?");
		if (status) {
			return true;
		} else {
			return false;
		}
	}
</script>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: 0000cd;
}

th, td {
	padding: 10px;
	text-align: left;
}
</style>
</head>
<body>

	<%@include file="header-inner.jsp"%>

	<h3>Case Worker Profiles</h3>

	<table>
		<thead>
			<tr>
				<th>S.No</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action(Edit/Delete)</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${caseWorkers eq null }">
				<tr>
					<td colspan="5">No Records Found</td>
				</tr>
			</c:if>
			<c:forEach items="${caseWorkers}" var="cw" varStatus="index">
				<tr>
					<td><c:out value="${index.count}" /></td>
					<td><c:out value="${cw.firstName }" /></td>
					<td><c:out value="${cw.lastName }" /></td>
					<td><c:out value="${cw.email }" /></td>
					<td><a href="editCaseWorker?bid=${cw.userId}"><img
							src="images/edit.png" width="20" height="20" /></a> &nbsp; &nbsp; <a
						href="deleteCaseWorker?bid=${book.userId}"><img
							src="images/delete.png" width="20" height="20"
							onclick="return confirmDelete()" /> </a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table>
		<tr>
			<c:if test="${cpn ne 1 }">
				<td><a href="viewCaseWorkers?cpn=${cpn-1}">Previous</a></td>
			</c:if>
			<c:forEach begin="1" end="${tp}" var="pageNo">
				<c:choose>
					<c:when test="${cpn==pageNo}">
						<td>${cpn}</td>
					</c:when>
					<c:otherwise>
						<td><a href="viewCaseWorkers?cpn=${pageNo}">${pageNo}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${cpn ne tp }">
				<td><a href="viewCaseWorkers?cpn=${cpn+1}">Next</a></td>
			</c:if>
		</tr>
	</table>
</body>
</html>