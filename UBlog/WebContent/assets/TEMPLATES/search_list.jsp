<%@page import="java.util.ArrayList"%>
<%@page import="Models.Blog"%>
<div id="content">
	
	<%
		ArrayList<Blog> blogs = (ArrayList<Blog>) request.getAttribute("blogs");
		response.getWriter().print(blogs.size());
	%>
	
</div>