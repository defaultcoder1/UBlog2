$(document).ready(function() {
	
	$(document).ajaxStart(function() {
        $("#logo").attr("src", "/UBlog/assets/IMG/preloader.gif");
    })
    .ajaxStop(function() {
    	$("#logo").attr("src", "/UBlog/assets/IMG/logos/logo_white.png");
    });
	
	/*function getBlogsByCategory(category) {
        $.ajax({
            url: "/UBlog/Search",
            type: "POST",
            cache: false,
            dataType: "HTML",
            data: {category: category},
            success: function (data) {
            	alert(data);
            }
        });
    }*/
	
});