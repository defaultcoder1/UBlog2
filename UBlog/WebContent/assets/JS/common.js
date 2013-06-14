$(document).ready(function() {
	
	$("#sign_out").click(function() {
		window.location = "/UBlog/Login";
	});
	$("#name_edit_profile").click(function() {
		window.location = "/UBlog/UserInfo";
	});
	
	$("#write_new_post").click(function() {
		window.location = "/UBlog/Publish";
	});
	$("#user_posts").click(function() {
		window.location = $(this).attr("href");
	});
	$("#user_subscribers").click(function() {
		$(".authorPop").hide();
		$(".subscriberPop").fadeIn();
	});
	$("#user_subscriptions").click(function() {
		$(".authorPop").hide();
		$(".subscribedPop").fadeIn();
	});
	
	
	$(document).keyup(function(event) {
		if(event.keyCode == 27) {
			$(".authorPop").hide();
		}
	});
	
	var popOffsetLeft = document.width/2 - 200;
	$(".authorPop").css("left", popOffsetLeft+"px");
	$(".authorPop tr").bind("click", function() {
		window.location = "/UBlog/UserPage?id="+$(this).attr("id");
	});
	$(".like_num").click(function() {
		$(".authorPop").hide();
		$(this).parent().parent().children(".authorPop").fadeIn();
	});
	$(".comment_like_num").click(function() {
		$(".authorPop").hide();
		$(this).parent().parent().parent().parent().parent().children(".authorPop").fadeIn();
	});
	$(".full_article_comment_like_num").on("click", function() {
		$(".authorPop").hide();
		$(this).parent().parent().parent().parent().parent().parent().children(".authorPop").fadeIn();
	});
	
	
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