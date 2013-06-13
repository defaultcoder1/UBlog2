$(document).ready(function() {
	
	editor_area.document.designMode = 'On';
	$("#editor_area").contents().find("body").css("font-family", "arial,sans-serif");
	$("#editor_area").contents().find("body").css("font-size", "13px");
	$("#editor_area").contents().find("body").css("color", "#444");
	
	$("#W_bold").click(function() { editor_area.document.execCommand('bold', false, null); });
	$("#W_italic").click(function() { editor_area.document.execCommand('italic', false, null); });
	$("#W_underline").click(function() { editor_area.document.execCommand('underline', false, null); });
	$("#W_size").click(function() {
		var fSize = prompt('Enter size 1-7', '');
		editor_area.document.execCommand('FontSize', false, fSize);
	});
	$("#W_textcolor").click(function() {
		var fColor = prompt('Define color', '');
		editor_area.document.execCommand('ForeColor', false, fColor);
	});
	$("#W_bgcolor").click(function() {
		var bgColor = prompt('Define color', '');
		editor_area.document.execCommand('hiliteColor', false, bgColor);
	});
	$("#W_image").click(function() {
		$("#gallery_pop").fadeIn();
		$("#gallery_pop").attr("parent", "2");
	});
	$("#W_link").click(function() {
		var linkURL = prompt('Type URL', 'http://');
		editor_area.document.execCommand('CreateLink', false, linkURL);
	});
	$("#W_indentless").click(function() { editor_area.document.execCommand('Outdent', false, null); });
	$("#W_indentmore").click(function() { editor_area.document.execCommand('Indent', false, null); });
	$("#W_olist").click(function() { editor_area.document.execCommand('InsertOrderedList', false, 'newOL'); });
	$("#W_ulist").click(function() { editor_area.document.execCommand('InsertUnorderedList', false, 'newUL'); });
	$("#W_aleft").click(function() { editor_area.document.execCommand('justifyleft', false, null); });
	$("#W_acenter").click(function() { editor_area.document.execCommand('justifycenter', false, null); });
	$("#W_aright").click(function() { editor_area.document.execCommand('justifyright', false, null); });
	
	
	//------------------------------------------------------------------------------------------------------------
	
	$("#cancel_button").click(function() { window.location = "UserPage"; });
	$("#publish_button").click(function() {
		$("#article_content").val($("#editor_area").contents().find("body").html());
	});
	
	$("#open_gallery").click(function() {
		$("#gallery_pop").fadeIn();
		$("#gallery_pop").attr("parent", "1")
	});
	
	$("body").bind('keyup', function(event) {
		if(event.keyCode == 27) {
			$("#gallery_pop").hide();
			$("#gallery_pop").attr("parent", "0");
		}
	});
	
	$("#gallery_pop").load(function() {
		$("#gallery_pop").contents().find("body").bind('keyup', function(event) {
			if(event.keyCode == 27) $("#gallery_pop").fadeOut();
		});
		$("#gallery_pop").contents().find("#Gallery_Images img").bind('click', function() {
			var src = $(this).attr("src");
			$("#gallery_pop").hide();
			if($("#gallery_pop").attr("parent") == "1"){
				$("#post_image_box").css("background-image", "none");
				$("#post_image_box").css("border", "none");
				$("#post_image").attr("src", src);
				$("#article_image").val(src);
			}else {
				editor_area.document.execCommand('insertimage', false, src);
			}
			$("#gallery_pop").attr("parent", "0");
		});
	});
});