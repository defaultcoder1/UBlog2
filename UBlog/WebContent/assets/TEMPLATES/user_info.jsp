<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Models.User"%>
<%@page import="Models.Blog" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Models.Author" %>

<%
	User u = (User) request.getAttribute("user");
	String subscribed_blogs = "";
	String user_blogs = "";
	if(request.getParameter("feed") == null) subscribed_blogs = "feed_filter_button_selected";
	else user_blogs = "feed_filter_button_selected";
	
	String subscribe_write = "<td id='subscribe2'  blogger='"+u.getId()+"' class='calibri";
	String subscribe_write_ending = "'>SUBSCRIBE</td>";
	
	String subscriberPop = "<div class='authorPop subscriberPop'><table class='authorPop_t' cellpadding='0' cellspacing='0' border='0'>";
	String subscribedPop = "<div class='authorPop subscribedPop'><table class='authorPop_t' cellpadding='0' cellspacing='0' border='0'>";
	ArrayList<Author> subscribers = ((ArrayList<Author>)request.getAttribute("subscribers"));
	ArrayList<Author> subscribed = ((ArrayList<Author>)request.getAttribute("subscriptions"));
	for(int i=0; i<subscribers.size(); i++) {
		subscriberPop += "<tr id='"+subscribers.get(i).getId()+"'><td class='pop_author_avatar'><img src='"+subscribers.get(i).getImage()+"' /></td><td class='pop_author_name calibri_bold'>"+subscribers.get(i).getName() + " " + subscribers.get(i).getLastName() +"</td></tr>";
		if(((User)request.getSession().getAttribute("user")).getId().equals(subscribers.get(i).getId()))
			subscribe_write_ending = " unsubscribe'>UNSUBSCRIBE</td>";
	}
	for(int i=0; i<subscribed.size(); i++) {
		subscribedPop += "<tr id='"+subscribed.get(i).getId()+"'><td class='pop_author_avatar'><img src='"+subscribed.get(i).getImage()+"' /></td><td class='pop_author_name calibri_bold'>"+subscribed.get(i).getName() + " " + subscribed.get(i).getLastName() +"</td></tr>";
	}
	subscriberPop += "</table></div>";
	subscribedPop += "</table></div>";
	
	subscribe_write += subscribe_write_ending;
	if(((User)request.getSession().getAttribute("user")).getId().equals(u.getId()))
		subscribe_write = "<td id='write_new_post' class='calibri'>Write New Post</td>";
%>
<%=subscriberPop %>
<%=subscribedPop %>

<div id="user_info">
  <table id="user_info_t" cellpadding="0" cellspacing="5" border="0" align="center">
		<tr>
			<td id="user_subscribers">
				<p id="subscribers_val" class="calibri_bold"><%=u.getSubscribersNum() %></p>
				<p id="subscribers_desc" class="calibri">Subscribers</p>
			</td>
			<td id="user_profile_avatar_container" rowspan="4">
				<img id="user_profile_avatar" src="<%=u.getImage() %>" />
				<p id="user_profile_name" class="calibri_bold"><%=u.getName() + " " + u.getLastName() %></p>
				<p id="user_profile_title" class="calibri"><%=u.getBlogName() %></p>
				<div id="additional_navigation">
					<table id="additional_navigation_t" cellpadding="0" cellspacing="0">
						<tr>
							<td id="additional_notifications_desc" class="calibri">Notifications</td>
							<td id="additional_notifications_val" class="calibri_bold">0</td>
							<td id="additional_messages_desc" class="calibri">Messages</td>
							<td id="additional_messages_val" class="calibri_bold">0</td>
							<td id="additional_placeholder"> </td>
							<%=subscribe_write %>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td id="user_subscriptions">
				<p id="subscriptions_val" class="calibri_bold"><%=u.getSubscriptionsNum() %></p>
				<p id="subscriptions_desc" class="calibri">Subscriptions</p>
			</td>
		</tr>
		<tr>
			<td id="user_posts" href="/UBlog/UserPage?id=<%=u.getId() %>&feed=userblogs">
				<p id="posts_val" class="calibri_bold"><%=request.getAttribute("articlenum") %></p>
				<p id="posts_desc" class="calibri">Posts</p>
			</td>
		</tr>
		<tr><td></td></tr>
	</table>
</div>



<div id="feed_filter">
  <table id="feed_filter_t" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td class="feed_filter_placeholder" width="30"> </td>
			<td class="feed_filter_buttons <%=subscribed_blogs %> calibri_bold" href="/UBlog/UserPage?id=<%=u.getId() %>">Subscribed Posts</td>
			<td class="feed_filter_buttons <%=user_blogs %> calibri_bold" href="/UBlog/UserPage?id=<%=u.getId() %>&feed=userblogs"><%=u.getName() %>'s Posts</td>
			<td class="feed_filter_placeholder"> </td>
		</tr>
	</table>
</div>
