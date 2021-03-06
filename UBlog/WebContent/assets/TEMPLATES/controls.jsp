<%@page import="Models.User"%>
<% User user = (User) request.getSession().getAttribute("user"); %>

<input type="hidden" id="default_authorPop_entry" value="<tr id='<%=user.getId() %>'><td class='pop_author_avatar'><img src='<%=user.getImage() %>' /></td><td class='pop_author_name calibri_bold'><%=user.getName() + " " + user.getLastName() %></td></tr>" />
<input type="hidden" id="superUserID" value="<%=user.getId() %>" />
<input type="hidden" id="superUserIMG" value="<%=user.getImage() %>" />


<div style="position:relative; height:40px;">
	<%@include file="dropDown.jsp" %>
</div>
<div id="controls">
	<table id="controls_t" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td id="search_container">
			<form id="searchForm" action="/UBlog/Search" method="post">
				<input type="text" id="search" name="search" spellcheck="false" autocomplete="off" placeholder="Search..." />
				<input type="hidden" name="category" id="category_value" value="<%=request.getParameter("category")!=null ? request.getParameter("category") : "All" %>" />
				<input type="hidden" name="searchInput" id="searchInput" value="1" />
			</form>
			</td>
			<td id="logo_container">
				<img id="logo" src="/UBlog/assets/IMG/logos/logo_white.png" />
			</td>
			<td id="notifications" class="user_icons"><img src="/UBlog/assets/IMG/notifications_gray.png" /></td>
			<td id="messages" class="user_icons"><img src="/UBlog/assets/IMG/messages_gray.png" /></td>
			<td id="write" class="user_icons"><img src="/UBlog/assets/IMG/write_gray.png" /></td>			
			<td id="more" class="user_icons">
				<img src="/UBlog/assets/IMG/arrow_gray.png" />
			</td>
			<td id="user_avatar_container">
				<a href="/UBlog/UserPage"><img id="user_avatar" src="<%=user.getImage() %>" /></a>
			</td>
		</tr>
	</table>
</div>