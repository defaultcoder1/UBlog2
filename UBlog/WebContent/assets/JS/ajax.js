$(document).ready(function() {
	
	$(document).ajaxStart(function() {
        $("#logo").attr("src", "/UBlog/assets/IMG/preloader.gif");
    })
    .ajaxStop(function() {
    	$("#logo").attr("src", "/UBlog/assets/IMG/logos/logo_white.png");
    });
	
	function likeArticle(articleid, which) {
        $.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {articleid: articleid, which: which},
            success: function (data) {}
        });
    }
	function likeComment(commentid, which) {
		$.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {commentid: commentid, which: which},
            success: function (data) {}
        });
	}
	function addSubscribe(towho, which) {
		$.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {towho: towho, which: which},
            success: function (data) {}
        });
	}
	function addSubscribe(towho, which) {
		$.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {towho: towho, which: which},
            success: function (data) {}
        });
	}
	function addComment(articleid, content, which) {
		$.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {articleid: articleid, content: content, which: which},
            success: function (data) {
            	$("#prependable").prepend(data);
            }
        });
	}
	
	
	
	
	$(".like").click(function() {
		var articleid = $(this).attr("articleid");
		var which = "likearticle";
		if($(this).hasClass("unlike")) which = "unlikearticle";
		likeArticle(articleid, which);
		var superUserID = $("#superUserID").val();
		if($(this).hasClass("unlike")) {
			$(this).removeClass("unlike");
			$(this).text("Like");
			var like_number = parseInt($(this).parent().children(".like_num").children(".like_number").text())-1;
			$(this).parent().children(".like_num").children(".like_number").text(like_number);
			$(this).parent().parent().children(".authorPop").children(".authorPop_t").find("#"+superUserID).remove();
		}else {
			$(this).addClass("unlike");
			$(this).text("Unike");
			var like_number = parseInt($(this).parent().children(".like_num").children(".like_number").text())+1;
			$(this).parent().children(".like_num").children(".like_number").text(like_number);
			$(this).parent().parent().children(".authorPop").children(".authorPop_t").prepend($("#default_authorPop_entry").val());
			Cufon.refresh();
		}
	});
	
	$(".comment_like").click(function() {
		var commentid = $(this).attr("commentid");
		var which = "likecomment";
		if($(this).hasClass("unlikeCommentClass")) which = "unlikecomment";
		likeComment(commentid, which);
		var superUserID = $("#superUserID").val();
		if($(this).hasClass("unlikeCommentClass")) {
			$(this).removeClass("unlikeCommentClass");
			$(this).text("Like");
			var like_number = parseInt($(this).parent().children(".comment_like_num").children(".comment_like_number").text())-1;
			$(this).parent().children(".comment_like_num").children(".comment_like_number").text(like_number);
			$(this).parent().parent().parent().parent().parent().children(".authorPop").children(".authorPop_t").find("#"+superUserID).remove();
		}
		else {
			$(this).addClass("unlikeCommentClass");
			$(this).text("Unlike");
			var like_number = parseInt($(this).parent().children(".comment_like_num").children(".comment_like_number").text())+1;
			$(this).parent().children(".comment_like_num").children(".comment_like_number").text(like_number);
			$(this).parent().parent().parent().parent().parent().children(".authorPop").children(".authorPop_t").prepend($("#default_authorPop_entry").val());
			Cufon.refresh();
		}
	});
	
	$("#subscribe2").click(function() {
		var blogger = $(this).attr("blogger");
		var which = "addsubscribe";
		if($(this).hasClass("unsubscribe")) which = "removesubscribe";
		addSubscribe(blogger, which);
		var superUserID = $("#superUserID").val();
		if($(this).hasClass("unsubscribe")) {
			$(this).removeClass("unsubscribe");
			$(this).text("SUBSCRIBE");
			var subscribers_val = parseInt($("#subscribers_val").text()) - 1;
			$("#subscribers_val").text(subscribers_val);
			$(".subscriberPop").children(".authorPop_t").find("#"+superUserID).remove();
		}else {
			$(this).addClass("unsubscribe");
			$(this).text("UNSUBSCRIBE");
			var subscribers_val = parseInt($("#subscribers_val").text()) + 1;
			$("#subscribers_val").text(subscribers_val);
			$(".subscriberPop").children(".authorPop_t").prepend($("#default_authorPop_entry").val());
		}
		Cufon.refresh();
	});
	
	$("#full_article_like").click(function() {
		var blogid = $(this).attr("blogid");
		var which = "likearticle";
		if($(this).hasClass("full_unlike")) which = "unlikearticle";
		likeArticle(blogid, which);
		var superUserID = $("#superUserID").val();
		if($(this).hasClass("full_unlike")) {
			$(this).removeClass("full_unlike");
			$(this).text("Like");
			var full_like_number = parseInt($("#full_article_like_number").text()) - 1;
			$("#full_article_like_number").text(full_like_number);
			$("#bloggers").find("#"+superUserID).remove();
		}else {
			$(this).addClass("full_unlike");
			$(this).text("Unike");
			var full_like_number = parseInt($("#full_article_like_number").text()) + 1;
			$("#full_article_like_number").text(full_like_number);
			$("#bloggers").prepend("<a href='/UBlog/UserPage?id="+superUserID+"'><img id='"+superUserID+"' src='"+$("#superUserIMG").val()+"' /></a>");
		}
	});
	
	
	$("#comment_button").click(function() {
		var blogid = $("#full_article_like").attr("blogid");
		var content = $("#add_comment_textarea").val();
		addComment(blogid, content, "addcomment");
	});
	
	
	$(".full_article_comment_like").on("click", function() {
		var commentid = $(this).attr("commentid");
		var which = "likecomment";
		if($(this).hasClass("unlikeCommentClass")) which = "unlikecomment";
		likeComment(commentid, which);
		var superUserID = $("#superUserID").val();
		if($(this).hasClass("unlikeCommentClass")) {
			$(this).removeClass("unlikeCommentClass");
			$(this).text("Like");
			var like_number = parseInt($(this).parent().children(".full_article_comment_like_num").children(".full_article_comment_like_number").text())-1;
			$(this).parent().children(".full_article_comment_like_num").children(".full_article_comment_like_number").text(like_number);
			$(this).parent().parent().parent().parent().parent().children(".authorPop").children(".authorPop_t").find("#"+superUserID).remove();
		}
		else {
			$(this).addClass("unlikeCommentClass");
			$(this).text("Unlike");
			var like_number = parseInt($(this).parent().children(".full_article_comment_like_num").children(".full_article_comment_like_number").text())+1;
			$(this).parent().children(".full_article_comment_like_num").children(".full_article_comment_like_number").text(like_number);
			$(this).parent().parent().parent().parent().parent().children(".authorPop").children(".authorPop_t").prepend($("#default_authorPop_entry").val());
			Cufon.refresh();
		}
	});
	
	
});