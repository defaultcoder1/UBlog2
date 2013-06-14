<%@page import="Models.User"%>
<%@page import="Models.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Blog"%>
<%@page import="java.lang.Object" %>
<style>
	#view_post {padding-top:25px; width:100%;}
	#view_post_inner_box {width:850px; margin:0 auto; background-color:#FFF;
		box-shadow:0px 0px 10px #FFF;}
	#view_post_inner_box_t {width:100%;}
	#full_author_image_container {width:45px; height:45px; padding:7px; padding-left:15px; padding-right:15px;
		text-align:center; vertical-align:middle;}
	#full_author_image {max-width:45px; max-height:45px; border-radius:2px;}
	#full_author_name_container {padding-right:10px; vertical-align:top;}
	#full_author_name {margin:0px; font-size:13px; color:#555; margin-left:10px; margin-top:15px;}
	#full_article_date {margin:0px; font-size:12px; color:#888; margin-left:10px; margin-top:6px;}
	#full_article_title_container {text-align:right; vertical-align:middle; padding-left:20px;
		padding-right:50px;}
	#full_article_title {margin:0px; font-size:16px; color:#507EAD; line-height:20px;}
	#full_article_content {padding:20px; font-family:arial,sans-serif; font-size:12px; color:#424242; line-height:20px;
		border-top:1px solid #E8E8E8;}
	#full_article_comment_separator {width:100%; height:20px; background-image:url(/UBlog/assets/IMG/body_bg.png);
		margin-top:25px; margin-bottom:25px;}
	#like_info {border-top:1px solid #DDD; padding:10px; font-family:arial,sans-serif; font-size:12px;
		color:#424242; padding-left:20px;}
	#full_article_like {font-size:12px; font-family:arial,sans-serif; color:#507EAD; font-weight:bold;
		cursor:pointer; padding-right:20px;}
	#full_article_like:hover {text-decoration:underline;}
	#full_article_like_num {font-family:arial,sans-serif; font-size:12px; color:#424242;}
	#full_article_like_number {font-weight:bold;}
	#bloggers {border-top:1px solid #DDD; padding:10px; padding-bottom:0px; overflow:auto; box-shadow:inset 0px 0px 10px #999;}
	#bloggers img {display:block; float:left; max-width:50px; max-height:50px; padding-left:10px; padding-bottom:10px;}
		
	#article_additional {width:850px; margin:0 auto;}
	#article_additional_t {width:100%;}
	#full_article_comments {width:530px; padding:20px; padding-top:0px; padding-left:0px;}
	.full_article_comment {width:100%; background-color:#FFF; box-shadow:0px 0px 10px #FFF; margin-bottom:7px;}
	.full_article_comment_t {width:100%;}
	.full_article_comment_author_avatar {width:60px; text-align:center; vertical-align:top; padding-top:10px;
		padding-bottom:10px;}
	.full_article_comment_author_avatar img {max-width:40px; max-height:40px;}
	.full_article_comment_content {vertical-align:top; padding:10px; font-family:arial,sans-serif; font-size:12px;
		line-height:18px; color:#666; padding-left:0px;}
	.full_article_comment_author_name {font-weight:bold; color:#507EAD; padding-right:7px;}
	.full_article_comment_date {margin:0px; margin-top:1px; font-size:11px; color:#888;}
	.full_article_comment_like {font-family:arial,sans-serif; font-size:12px; color:#507EAD; padding-left:10px;
		cursor:pointer;}
	.full_article_comment_like:hover {text-decoration:underline;}
	.full_article_comment_like_num {float:right; font-size:12px; font-family:arial,sans-serif; color:#555; cursor:pointer;}
	.full_article_comment_like_num:hover {text-decoration:underline;}
	.full_article_comment_like_number {font-weight:bold;}
	
	#add_comment_box {width:100%; margin-bottom:10px; border-bottom:1px solid #CCC; padding-bottom:7px;}
	#add_comment_box_t {width:100%;}
	#add_comment_textarea {width:100%; height:50px; resize:none; border:none; font-family:arial,sans-serif;
		font-size:12px; color:#424242; outline:none; box-shadow:inset 0px 0px 7px #222;}
	#comment_button {font-family:arial,sans-serif; font-size:12px; font-weight:bold; color:#507EAD;
		border:1px solid #48749D; padding:8px; padding-left:20px; padding-right:20px; border-radius:2px;
		cursor:pointer; background-image:-webkit-linear-gradient(top, #F5F5F5, #F0F0F0); position:relative;
		margin-top:5px; margin-right:-4px;}
	#comment_button:hover {background-image:-webkit-linear-gradient(top, #F0F0F0, #F5F5F5);}
	#comment_button:active {box-shadow:inset 0px 2px 3px #555;}
</style>


<%
	User u = (User) request.getSession().getAttribute("user");
	Blog b = (Blog) request.getAttribute("blog");
	ArrayList<Comment> cl = b.getComments();
	String likeValue = "Like";
	String unlike = "";
	for(int i=0; i<b.getLikeNum(); i++) {
		if(b.getLikes().get(i).getAuthorId().equals(u.getId())) {
			likeValue = "Unlike";
			unlike = "full_unlike";
			break;
		}
	}
%>


<div id="content">
	
	<div id="view_post">
		<div id="view_post_inner_box">
			<table id="view_post_inner_box_t" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td id="full_author_image_container">
						<a href="/UBlog/UserPage?id=<%=b.getAuthorId() %>"><img id="full_author_image" src="<%=b.getAuthorImage() %>" /></a>
					</td>
					<td id="full_author_name_container">
						<p id="full_author_name" class="calibri_bold"><a style="text-decoration:none; color:inherit;" href="/UBlog/UserPage?id=<%=b.getAuthorId() %>"><%=b.getAuthorName() + " " + b.getAuthorLastName() %></a></p>
						<p id="full_article_date" class="calibri"><%=b.getDate() %></p>
					</td>
					<td id="full_article_title_container">
						<p id="full_article_title" class="calibri"><%=b.getTitle() %></p>
					</td>
				</tr>
				<tr>
					<td id="full_article_content" colspan="3">
						<%=b.getContent() %>
					</td>
				</tr>
				<tr>
					<td id="bloggers" colspan="3">
						<%
							for(int i=0; i<b.getLikeNum(); i++) {
						%>
						
								<a href="/UBlog/UserPage?id=<%=b.getLikes().get(i).getAuthorId() %>"><img id="<%=b.getLikes().get(i).getAuthorId() %>" src="<%=b.getLikes().get(i).getAuthorImage() %>" /></a>
						
						<% } %>
					</td>
				</tr>
				<tr>
					<td id="like_info" colspan="3">
						<font id="full_article_like" blogid="<%=b.getId() %>" class="<%=unlike %>"><%=likeValue %></font>
						<font id="full_article_like_num">
							<font id="full_article_like_number"><%=b.getLikeNum() %></font>
							bloggers like this
						</font>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="full_article_comment_separator"></div>
	
	<div id="article_additional">
		<table id="article_additional_t" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td id="full_article_comments">
				
					<div id="add_comment_box">
						<table id="add_comment_box_t">
							<tr>
								<td align="right">
									<textarea id="add_comment_textarea"></textarea>
									<button id="comment_button">Comment</button>
								</td>
							</tr>
						</table>
					</div>
				
				
					<div id="prependable"></div>
				
					<%
						for(int i=0; i<cl.size(); i++) {
							Comment c = cl.get(i);
							String likeCommentValue = "Like";
							String unlikeCommentClass = "";
							String commentLikers = "<div class='authorPop'><table class='authorPop_t' cellpadding='0' cellspacing='0' border='0'>";
							for(int j=0; j<c.getLikes().size(); j++) {
								commentLikers += "<tr id='"+c.getLikes().get(j).getAuthorId()+"'><td class='pop_author_avatar'><img src='"+c.getLikes().get(j).getAuthorImage()+"' /></td><td class='pop_author_name calibri_bold'>"+c.getLikes().get(j).getAuthorName() + " " + c.getLikes().get(j).getAuthorLastName() +"</td></tr>";
								if(c.getLikes().get(j).getAuthorId().equals(u.getId()))
									likeCommentValue = "Unlike";
									unlikeCommentClass = "unlikeCommentClass";
							}
							commentLikers += "</table></div>";
					%>
					
					<div class="full_article_comment">
						<%=commentLikers %>
						<table class="full_article_comment_t" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="full_article_comment_author_avatar">
									<a href="/UBlog/UserPage?id=<%=cl.get(i).getAuthorId() %>"><img src="<%=cl.get(i).getAuthorImage() %>" /></a>
								</td>
								<td class="full_article_comment_content">
									<font class="full_article_comment_author_name"><a style="text-decoration:none; color:inherit;" href="/UBlog/UserPage?id=<%=cl.get(i).getAuthorId() %>"><%=cl.get(i).getAuthorName() + " " + cl.get(i).getAuthorLastName() %></a></font>
									<%=cl.get(i).getContent() %>
									<p class="full_article_comment_date">
										<%=cl.get(i).getDate() %>
										<font class="full_article_comment_like <%=unlikeCommentClass %>" commentid="<%=c.getId() %>"><%=likeCommentValue %></font>
										<font class="full_article_comment_like_num">
											<font class="full_article_comment_like_number"><%=cl.get(i).getLikes().size() %></font>
											bloggers like this
										</font>
									</p>
								</td>
							</tr>
						</table>
					</div>
					
					<% } %>
					
				</td>
				<td id="article_extra">---</td>
			</tr>
		</table>
	</div>
	
</div>