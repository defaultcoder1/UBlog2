<%@page import="Models.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Blog"%>
<%@page import="Models.User" %>

<div id="common_feed">
	<div id="common_feed_inner_box">
		
		<%
			String myID = ((User) request.getSession().getAttribute("user")).getId();
			ArrayList<Blog> blogs = (ArrayList<Blog>) request.getAttribute("blogs");
			for(int i=0; i<blogs.size(); i++) {
				String unlike = "";
				String likeValue = "Like";
				for(int k=0; k<blogs.get(i).getLikes().size(); k++) {
					if(blogs.get(i).getLikes().get(k).getAuthorId().equals(myID)) {
						unlike = " unlike";
						likeValue = "Unlike";
						break;
					}
				}
		%>
		
		<div class="article" style="<%=(i%2 == 0) ? "float:left; clear:left" : "float:right; clear:right;" %>">
			<table class="article_t" cellpading="0" cellspacing="0" border="0">
				<tr>
					<td class="author_avatar" rowspan="2"><img src="<%=blogs.get(i).getAuthorImage() %>" /></td>
					<td class="article_title"><a href="/UBlog/Blog?id=<%=blogs.get(i).getId() %>"><%=blogs.get(i).getTitle() %></a> (<%=blogs.get(i).getCategory()%>)</td>
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
							<span class="like<%=unlike %>" articleid="<%=blogs.get(i).getId() %>"><%=likeValue %></span>
							<span class="view_comments" style="<%=blogs.get(i).getCommentNum() == 0 ? "display:none;" : "" %>">View Comments</span>
							<span class="like_num"><%=blogs.get(i).getLikeNum() %> bloggers like this</span>
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
									<td class="comment_author_name"><%=c.getAuthorName() + " " %><%=c.getAuthorLastName() %></td>
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