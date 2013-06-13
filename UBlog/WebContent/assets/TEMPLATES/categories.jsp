<link rel="stylesheet" href="/UBlog/assets/CSS/categories.css" />

<div id="categories" category="<%=(request.getParameter("category") == null) ? "All" : request.getParameter("category") %>">
	<table id="categories_t" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="category_item calibri_bold" category="All">All</td>
			<td class="category_item calibri_bold" category="Art">Art</td>
			<td class="category_item calibri_bold" category="Business">Business</td>
			<td class="category_item calibri_bold" category="Education">Education</td>
			<td class="category_item calibri_bold" category="Science">Science</td>
			<td class="category_item calibri_bold" category="Society">Society</td>
			<td class="category_item calibri_bold" category="Sport">Sport</td>
			<td class="category_item calibri_bold" category="Technologies">Technologies</td>
		</tr>
	</table>
</div>