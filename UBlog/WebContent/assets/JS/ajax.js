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
	
	$("#search").keyup(function(event) {
		if(event.keyCode == 13) {
			$("#searchForm").submit();
		}
	});
	
	$(".categorie_item").click(function() {
		var catValue = $(this).text();
		window.location = "/UBlog/Search?category="+catValue;
		//$(".categorie_selected").removeClass("categorie_selected");
		//$(this).addClass("categorie_selected");
	});
	
});