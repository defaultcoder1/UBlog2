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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var user = new String();

	function writeMessage(data, textStatus, jqXHR) {
		if (data != "")
			console.log(data);
	}

	function checkNews() {
		var url = "http://localhost:8080/UBlog/chat.do?from=user0";

		$.ajax(url, {
			success : writeMessage,
		}).always(function() {
			checkNews();		
		});
	}

	$(document).ready(function() {
		$("categorie_item").click(function() {
			user = $(this).attr("id"); 
		});

		$("#logo").click(function() {
			var url = "http://localhost:8080/UBlog/chat.do?to=user0&from=" + user;
			url += "&msg=" + $("#search").val();

			$("#search").val("");

			$.ajax(url);
		});

		checkNews();
	});
	
});