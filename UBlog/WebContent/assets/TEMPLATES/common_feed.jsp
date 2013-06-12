<%@page import="Models.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Blog"%>

<div id="common_feed">
	<div id="common_feed_inner_box">
		
		<%
			ArrayList<Blog> blogs = (ArrayList<Blog>) request.getAttribute("blogs");
			if(blogs == null) {
				blogs = (ArrayList<Blog>) request.getAttribute("myblogs");
			}
			for(int i=0; i<blogs.size(); i++) {
		%>
		
		<div class="article">
			<table class="article_t" cellpading="0" cellspacing="0" border="0">
				<tr>
					<td class="author_avatar" rowspan="2"><img src="/UBlog/assets/IMG/no_avatar.png" /></td>
					<td class="article_title"><a href="/UBlog/Blog?id=<%=blogs.get(i).getId() %>"><%=blogs.get(i).getTitle() %></a></td>
				</tr>
				<tr><td class="article_date"><%=blogs.get(i).getDate() %></td></tr>
				<tr>
					<% if(blogs.get(i).getImage() != null) { %>
					<td class="article_image" colspan="2">
						<div class="article_image_container">
							<img src="<%=blogs.get(i).getImage() %>" />
						</div>
					</td>
					<% } %>
				</tr>
				<tr>
					<td class="article_content" colspan="2">
						<div class="article_content_container">
							 <%=blogs.get(i).getContent() %>
						</div>
					</td>
				</tr>
				<tr>
					<td class="like_comment" colspan="2">
						<div class="like_comment_container">
							<span class="like">Like</span>
							<span class="view_comments">View Comments</span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="article_comments" colspan="2">
						<div class="article_comment">
							<table class="article_comment_t" cellpadding="0" cellspacing="0" border="0">
							<%
								ArrayList<Comment> cList = blogs.get(i).getComments();
								for(int k=0; k<cList.size(); k++) {
									Comment c = cList.get(k);
							%>
								<tr>
									<td class="comment_author_avatar" rowspan="2">
										<img src="<%=c.getAuthorImage() %>" />
									</td>
									<td class="comment_author_name"><%=c.getAuthorLastName() %></td>
								</tr>
								<tr>
									<td class="comment_content"><%=c.getContent() %></td>
								</tr>
							<% } %>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>	
		
		<% } %>		
		
	</div>		
</div>