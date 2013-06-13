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
            dataType: "HTML",
            data: {articleid: articleid, which: which},
            success: function (data) {
            
            	
            }
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
	
});