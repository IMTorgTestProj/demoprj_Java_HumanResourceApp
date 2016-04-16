<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<jsp:include page="common/menu.jsp"/>
		</div>
		<div id="container">
			<div id="container_header">
				<h4>Home Page</h4>
			</div>
			<div id="container_body">
			<p>This is home page of Human Resource System. Application.</p>				
				
			</div>
		</div>
		<div id="bfooter">
			<p>Copyright &copy; 2016 &middot; All Rights Reserved.</p>
		</div>
	</div>

</body>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bMin.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/_main.css">
<script type="text/javascript" language="javascript" src="<%=contextPath %>/js/jMin.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/js/bMin.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/js/_main.js"></script>
</html>