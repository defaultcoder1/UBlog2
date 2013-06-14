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
		%>
		
		<div class="article" style="<%=(i%2 == 0) ? "float:left; clear:left" : "float:right; clear:right;" %>">		
			<table class="article_t" cellpading="0" cellspacing="0" border="0">
				<tr>
					<td class="author_avatar" rowspan="2"><a href="/UBlog/UserPage?id=<%=blogs.get(i).getAuthorId() %>"><img src="<%=blogs.get(i).getAuthorImage() %>" /></a></td>
					<td class="article_title">
						<a href="/UBlog/Blog?id=<%=blogs.get(i).getId() %>"><%=blogs.get(i).getTitle() %></a>
						<span class="article_category_container calibri_bold"><%=blogs.get(i).getCategory() %></span>
					</td>
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
						<div class="article_content_after"></div>
					</td>
				</tr>
				<tr>
					<td class="like_comment" colspan="2">
						<%
							String unlike = "";
							String likeValue = "Like";
							String articleLikers = "<div class='authorPop'><table class='authorPop_t' cellpadding='0' cellspacing='0' border='0'>";
							for(int k=0; k<blogs.get(i).getLikes().size(); k++) {
								articleLikers += "<tr id='"+blogs.get(i).getLikes().get(k).getAuthorId()+"'><td class='pop_author_avatar'><img src='"+blogs.get(i).getLikes().get(k).getAuthorImage()+"' /></td><td class='pop_author_name calibri_bold'>"+blogs.get(i).getLikes().get(k).getAuthorName() + " " + blogs.get(i).getLikes().get(k).getAuthorLastName() +"</td></tr>";
								if(blogs.get(i).getLikes().get(k).getAuthorId().equals(myID)) {
									unlike = " unlike";
									likeValue = "Unlike";
								}
							}
							articleLikers += "</table></div>";
						%>
						<%=articleLikers %>
						<div class="like_comment_container">
							<span class="like<%=unlike %>" articleid="<%=blogs.get(i).getId() %>"><%=likeValue %></span>
							<span class="view_comments" style="<%=blogs.get(i).getCommentNum() == 0 ? "display:none;" : "" %>">Show Comments</span>
							<span class="like_num"><font class="like_number"><%=blogs.get(i).getLikeNum() %></font> bloggers like this</span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="article_comments" colspan="2">
								<%	ArrayList<Comment> cList = blogs.get(i).getComments();
									for(int k=0; k<cList.size(); k++) {
									Comment c = cList.get(k);
									String likeCommentValue = "Like";
									String unlikeCommentClass = "";
									String commentLikers = "<div class='authorPop'><table class='authorPop_t' cellpadding='0' cellspacing='0' border='0'>";
									for(int j=0; j<c.getLikes().size(); j++) {
										commentLikers += "<tr id='"+c.getLikes().get(j).getAuthorId()+"'><td class='pop_author_avatar'><img src='"+c.getLikes().get(j).getAuthorImage()+"' /></td><td class='pop_author_name calibri_bold'>"+c.getLikes().get(j).getAuthorName() + " " + c.getLikes().get(j).getAuthorLastName() +"</td></tr>";
										if(c.getLikes().get(j).getAuthorId().equals(myID))
											likeCommentValue = "Unlike";
											unlikeCommentClass = "unlikeCommentClass";
									}
									commentLikers += "</table></div>";
								%>
						<div class="article_comment">
									
							<%=commentLikers %>
							
							<table class="article_comment_t" cellpadding="0" cellspacing="0" border="0">							
								<tr>
									<td class="comment_author_avatar" rowspan="2">
										<a href="/UBlog/UserPage?id=<%=c.getAuthorId() %>"><img src="<%=c.getAuthorImage() %>" /></a>
									</td>
									<td class="comment_content_container">
										<font class="comment_author_name"><a style="color:inherit; text-decoration:none;" href="/UBlog/UserPage?id=<%=c.getAuthorId() %>"><%=c.getAuthorName() + " " %><%=c.getAuthorLastName() %></a></font>
										<font class="comment_content"><%=c.getContent() %></font>
									</td>
								</tr>
								<tr>
									<td class="comment_date">
										<%=c.getDate() %><span class="comment_like <%=unlikeCommentClass %>" commentid="<%=c.getId() %>"><%=likeCommentValue %></span>
										<span class="comment_like_num"><font class="comment_like_number"><%=c.getLikes().size() %></font> bloggers like this</span>
									</td>
								</tr>
							</table>
						</div>
						
						<% } %>
						
					</td>
				</tr>
			</table>
		</div>	
		
		<% } %>		
		
	</div>		
</div>
