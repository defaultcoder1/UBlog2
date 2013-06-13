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
	
	$(".like").click(function() {
		var articleid = $(this).attr("articleid");
		var which = "likearticle";
		if($(this).hasClass("unlike")) which = "unlikearticle";
		likeArticle(articleid, which);
		if($(this).hasClass("unlike")) {
			$(this).removeClass("unlike");
			$(this).text("Like");
		}else {
			$(this).addClass("unlike");
			$(this).text("Unike");
		}
	});
	
	$(".comment_like").click(function() {
		var commentid = $(this).attr("commentid");
		var which = "likecomment";
		if($(this).text() == "Unike") which = "unlikecomment";
		likeComment(commentid, which);
		if($(this).text() == "Unlike") $(this).text("Like");
		else $(this).text("Unlike");
	});
	
});