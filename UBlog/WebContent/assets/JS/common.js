$(document).ready(function() {
	
	var category = $("#categories").attr("category");
	$(".category_item[category = '"+category+"']").addClass("category_selected");

	$("#logo").click(function() {
		window.location = "Index";
	});
	
	$(".user_icons").mouseover(function() {
		var src = $(this).children("img").attr("src");
		var newSrc = src.replace("gray", "white");
		$(this).children("img").attr("src", newSrc);
	}).mouseleave(function() {
		var src = $(this).children("img").attr("src");
		var newSrc = src.replace("white", "gray");
		$(this).children("img").attr("src", newSrc);
	});
	
	$("#more").click(function() {
		$("#arrow_dropDown").slideToggle();
	});
	$("#notifications").click(function() {
		$("#notification_dropDown").slideToggle();
	});
	
	$("#write").click(function() { window.location = "Publish"; });
	
	$("#search").focus(function() {
		$(this).css("background-color", "#FFF");
		$(this).animate({
			width: 190,
			'background-position-x': '206px'
		});
	}).blur(function() {
		$(this).css("background-color", "#E0E0E0");
		$(this).animate({
			width: 160,
			'background-position-x': '176px'
		});
	});
	
	$("#search").keyup(function(event) {
		if(event.keyCode == 13) {
			$("#searchForm").submit();
		}
	});
	
	$(".category_item").click(function() {
		var catValue = $(this).text();
		window.location = "/UBlog/Search?category="+catValue;
	});
	
	$(".view_comments").click(function() {
		if($(this).hasClass("hide_comments")) {
			$(this).removeClass("hide_comments");
			$(this).text("Show Comments");
			$(this).parent().parent().parent().parent().children("tr").children(".article_comments").hide();
		}else {
			$(this).addClass("hide_comments");
			$(this).text("Hide Comments")
			$(this).parent().parent().parent().parent().children("tr").children(".article_comments").show();
		}
	});
	
	
	$(".feed_filter_buttons").click(function() {
		var url = $(this).attr("href");
		window.location = url;
	});
});