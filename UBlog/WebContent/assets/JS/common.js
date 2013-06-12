$(document).ready(function() {

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
	
	$(".categorie_item").click(function() {
		$(".categorie_selected").removeClass("categorie_selected");
		$(this).addClass("categorie_selected");
	});
});