
<%
	String contextPath = request.getContextPath();
%>
<ul id="menu" class="nav nav-tabs">
	<li><a href="<%=contextPath%>/home.html">Home</a></li>
	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Employees<span class="caret"></span></a>
		<ul class="dropdown-menu">
			<li><a href="<%=contextPath%>/employee/Controller?action=add">New</a></li>
			<li><a href="<%=contextPath%>/employee/Controller?action=list">List (All Employee)</a></li>
			<li><a href="<%=contextPath%>/employee/search.html">Search</a></li>
		</ul></li>
	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Jobs<span class="caret"></span></a>
		<ul class="dropdown-menu">
			<li><a href="<%=contextPath%>/job/add.html">New</a></li>
			<li><a href="<%=contextPath%>/job/Controller?action=list">List (All Jobs)</a></li>
			<li><a href="<%=contextPath%>/job/search.html">Search</a></li>
		</ul></li>
	<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Assignment<span class="caret"></span></a>
		<ul class="dropdown-menu">
			<li><a href="<%=contextPath%>/assignment/Controller?action=add">New Assignment</a></li>
			<li><a href="<%=contextPath%>/assignment/Controller?action=list">Employee Assignment</a></li>
			<li><a href="<%=contextPath%>/searchjobassignment.html">Search Assignment</a></li>
		</ul></li>
	<li><a href="#">Help</a></li>
</ul>