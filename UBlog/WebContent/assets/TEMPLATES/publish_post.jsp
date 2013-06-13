<link type="text/css" rel="stylesheet" href="/UBlog/assets/CSS/publish.css" />
<script type="text/javascript" src="/UBlog/assets/JS/WYSIWYG.js"></script>

<div id="content">
	
	<iframe id="gallery_pop" src="/UBlog/Gallery"></iframe>
		
	<form id="article_form" action="/UBlog/Index" method="post">
	<div id="editor_container">
		<table id="editor_container_t" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td id="post_title">
					<input type="text" spellcheck="false" placeholder="Post Title:" name="article_title" />
				</td>
				<td id="post_buttons">
					<button type="button" id="publish_button">PUBLISH</button>
					<button type="button" id="cancel_button">CANCEL</button>
				</td>
			</tr>
			<tr>
				<td id="WYSIWYG_controls" colspan="2">
					<div class="wysiwyg_button_container" id="W_bold"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_italic"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_underline"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_size"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_textcolor"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_bgcolor"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_image"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_link"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_olist"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_ulist"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_indentless"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_indentmore"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_aleft"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_acenter"><div class="wysiwyg_button"></div></div>
					<div class="wysiwyg_button_container" id="W_aright"><div class="wysiwyg_button"></div></div>
				</td>
			</tr>
			<tr>
				<td id="editor_area_container">
					<iframe id="editor_area"></iframe>
					<input type="hidden" name="article_content" id="article_content" />
				</td>
				<td id="image_tags" rowspan="2">
					<div id="post_image_box"><img id="post_image" src="" /></div>
					<button type="button" id="open_gallery">Choose from gallery</button>
					<input type="hidden" name="article_image" id="article_image" />
					
					<textarea name="article_tags"></textarea>
				</td>	
			</tr>
		</table>
	</div>
	</form>
	
</div>