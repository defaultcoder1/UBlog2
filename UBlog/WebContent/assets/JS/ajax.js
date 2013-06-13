$(document).ready(function() {
	
	$(document).ajaxStart(function() {
        $("#logo").attr("src", "/UBlog/assets/IMG/preloader.gif");
    })
    .ajaxStop(function() {
    	$("#logo").attr("src", "/UBlog/assets/IMG/logos/logo_white.png");
    });
	
	function likeArticle(articleid) {
        $.ajax({
            url: "/UBlog/SimpleTasks",
            type: "POST",
            cache: false,
            dataType: "HTML",
            data: {articleid: articleid, which: "likearticle"},
            success: function (data) {
            	alert("liked");
            }
        });
    }
	
	$(".like").click(function() {
		likeArticle($(this).attr("articleid"));
	});
	
});