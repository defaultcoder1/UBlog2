<%@page import="java.util.ArrayList"%>
<script type="text/javascript" src="/UBlog/assets/JS/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/UBlog/assets/JS/Cufon/cufon-yui.js"></script>
<script type="text/javascript" src="/UBlog/assets/JS/Cufon/calibri.cufonfonts.js"></script>
<script type="text/javascript">
	Cufon.replace('.calibri_bold', { fontFamily: 'Calibri Bold', hover: true });
	$(document).ready(function() {
		$("#Upload_Button").click(function() { $("#file").click(); });
		$("#file").change(function() { $("#form").submit(); });
	});
</script>


<style>
	body {margin:0px;}
	#Gallery {width:900px; height:520px; overflow:auto; padding-top:20px;
	padding-bottom:20px; background-image:url(/UBlog/assets/IMG/trans_black.png); z-index:1;}
	#Gallery_Header {}
	#Gallery_Header_Title {margin:0px; margin-left:30px; margin-bottom:20px; font-size:20px; color:#FFF;}
	#Gallery_Images {width:100%; border-top:1px solid #FFF; padding-top:20px; padding-bottom:20px; overflow:auto;}
	#Gallery_Images img {padding:5px; background-color:#000; max-width:100px; max-height:100px; display:block;
		float:left; margin-left:30px; margin-bottom:30px; cursor:pointer; border-radius:3px;}
	#Gallery_Images img:hover {background-color:#BBB;}
	#Upload_Button {font-family:arial,sans-serif; font-size:12px; font-weight:bold; color:#507EAD; border:1px solid #48749D;
	padding:8px; padding-left:20px; padding-right:20px; border-radius:2px; cursor:pointer;
	background-image:-webkit-linear-gradient(top, #F5F5F5, #F0F0F0); position:absolute; right:30px; top:10px;}
	#Upload_Button:hover {background-image:-webkit-linear-gradient(top, #F0F0F0, #F5F5F5);}
	#Upload_Button:active {box-shadow:inset 0px 2px 3px #555;}
</style>

<div id="Gallery">
	<div id="Gallery_Header">
		<p id="Gallery_Header_Title" class="calibri_bold">Choose from below or upload:</p>
		<form style="display:none;" id="form" action="/UBlog/Gallery" method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="file" />
		</form>
		<button id="Upload_Button">Choose Image</button>
	</div>
	<div id="Gallery_Images">
	<%
		ArrayList<String> images = (ArrayList<String>) request.getAttribute("images");
		for(int i=0; i<images.size(); i++) {
	%>
		<img src="<%=images.get(i) %>" />
	<% } %>
	</div>
</div>