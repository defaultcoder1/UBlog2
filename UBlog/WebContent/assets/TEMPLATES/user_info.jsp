<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Models.User"%>

<% User u = (User) request.getAttribute("user"); %>

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
							<td id="write_new_post" class="calibri" onclick="window.location='/UBlog/Publish'">Write New Post</td>
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
			<td id="user_posts">
				<p id="posts_val" class="calibri_bold">1</p>
				<p id="posts_desc" class="calibri">Posts</p>
			</td>
		</tr>
		<tr><td></td></tr>
	</table>
</div>
