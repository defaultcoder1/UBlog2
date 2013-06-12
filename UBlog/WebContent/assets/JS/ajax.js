$(document).ready(function() {
	
	$(document).ajaxStart(function() {
        $("#logo").attr("src", "/UBlog/assets/IMG/preloader.gif");
    })
    .ajaxStop(function() {
    	$("#logo").attr("src", "/UBlog/assets/IMG/logos/logo_white.png");
    });
	
	function comet() {
        $.ajax({
            url: "/UBlog/notificationServlet",
            type: "POST",
            cache: false,
            dataType: "TEXT",
            data: {},
            success: function (data) {
            	console.log("prochantali");
            }
        });
    }
	
	$(".categorie_item").click(function() {
		comet();
	});
});