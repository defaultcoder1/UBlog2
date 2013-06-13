<%@page import="Models.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="oracle.jrockit.jfr.settings.JSONElement"%>
<%@page import="Models.Blog"%>
<%@page import="java.lang.Object" %>
<style>
	#view_post {padding-top:25px; width:100%;}
	#view_post_inner_box {width:850px; margin:0 auto; background-color:#FFF;
		box-shadow:0px 0px 10px #FFF;}
	#view_post_inner_box_t {}
	#full_author_image_container {width:45px; height:45px; padding:7px; padding-left:15px; padding-right:15px;
		text-align:center; vertical-align:middle;}
	#full_author_image {max-width:45px; max-height:45px; border-radius:2px;}
	#full_author_name_container {padding-right:10px; vertical-align:top;}
	#full_author_name {margin:0px; font-size:13px; color:#555; margin-left:10px; margin-top:15px;}
	#full_article_date {margin:0px; font-size:12px; color:#888; margin-left:10px; margin-top:6px;}
	#full_article_title_container {width:500px; text-align:right; vertical-align:middle; padding-left:20px;
		padding-right:50px;}
	#full_article_title {margin:0px; font-size:16px; color:#507EAD; line-height:20px;}
	#full_article_content {padding:20px; font-family:arial,sans-serif; font-size:12px; color:#424242; line-height:20px;
		border-top:1px solid #E8E8E8;}
	#full_article_comment_separator {width:100%; height:20px; background-image:url(/UBlog/assets/IMG/body_bg.png);
		margin-top:25px; margin-bottom:25px;}
		
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
	.full_article_comment_date {margin:0px; margin-top:1px; font-size:11px; color:#888; text-align:right;}
	
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
	Blog b = (Blog) request.getAttribute("blog");
	ArrayList<Comment> cl = b.getComments();
%>


<div id="content">
	
	<div id="view_post">
		<div id="view_post_inner_box">
			<table id="view_post_inner_box_t" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td id="full_author_image_container">
						<img id="full_author_image" src="<%=b.getAuthorImage() %>" />
					</td>
					<td id="full_author_name_container">
						<p id="full_author_name" class="calibri_bold"><%=b.getAuthorName() + " " + b.getAuthorLastName() %></p>
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
				
				
					<%
						for(int i=0; i<cl.size(); i++) {
					%>
				
					<div class="full_article_comment">
						<table class="full_article_comment_t" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="full_article_comment_author_avatar">
									<img src="<%=cl.get(i).getAuthorImage() %>" />
								</td>
								<td class="full_article_comment_content">
									<font class="full_article_comment_author_name"><%=cl.get(i).getAuthorName() + " " + cl.get(i).getAuthorLastName() %></font>
									<%=cl.get(i).getContent() %>
									<p class="full_article_comment_date"><%=cl.get(i).getDate() %></p>
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