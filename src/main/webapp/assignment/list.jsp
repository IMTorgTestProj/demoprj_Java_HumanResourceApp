<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Human Resource System</title>
</head>
<body>

	<div id="wrapper" class="container">
		<div id="top_header">
			<h2 id="Project_header" class="title_color">Human Resource System</h2>
		</div>
		<div id="menu">
			<jsp:include page="../common/menu.jsp"></jsp:include>
		</div>
		<div id="container">
			<div id="container_header">
				<h4>Job Assignment List</h4>
			</div>
			<div id="container_body">
				<div id="table_body" class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead class="thead-inverse">
							<tr>
								<th>Last Name</th>
								<th>MI</th>
								<th>First Name</th>								
								<th>Job Number</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${assignments}" var="assignment">
								<tr>
									<td><c:out value="${assignment.lastName}" /></td>
									<td><c:out value="${assignment.initMiddle}" /></td>
									<td><c:out value="${assignment.firstName}" /></td>									
									<td><c:out value="${assignment.jobNumber}" /></td>
									<td class="actioncol"><a href="<%=contextPath%>/assignment/Controller?action=view&id=<c:out value="${assignment.assignmentId}"/>">view</td>
									<td class="actioncol"><a href="<%=contextPath%>/assignment/Controller?action=edit&id=<c:out value="${assignment.assignmentId}"/>">edit</td>
									<td class="actioncol"><a href="<%=contextPath%>/assignment/Controller?action=delete&id=<c:out value="${assignment.assignmentId}"/>">delete</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>

			</div>
		</div>
		<div id="bfooter">
			<p>Copyright &copy; 2016 &middot; All Rights Reserved.</p>
		</div>
	</div>

</body>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/bMin.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/_main.css">
<script type="text/javascript" language="javascript" src="<%=contextPath%>/js/jMin.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/js/bMin.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/js/hr.main.js"></script>
</html>
