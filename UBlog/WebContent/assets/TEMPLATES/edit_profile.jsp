<div id="content">

	<style type="text/css">
		#edit_profile {width:250px; padding:20px; background-color:#FFF; margin:0 auto;
			box-shadow:0px 0px 20px #FFF; text-align:center;}
		#edit_profile input {width:200px; font-family:arial,sans-serif; font-size:13px; color:#424242;
			padding:7px; border:1px solid #D9D9D9; border-top:1px solid #C0C0C0; outline-color:#507EAD;}
		#submit {font-family:arial,sans-serif; font-size:12px; font-weight:bold; color:#FFF; border:1px solid #48749D;
				padding:8px; padding-left:20px; padding-right:20px; border-radius:2px; cursor:pointer;
				background-image:-webkit-linear-gradient(top, #507EAD, #48749D); margin-top:20px;}
		#submit:hover {background-image:-webkit-linear-gradient(top, #48749D, #507EAD);}
		#submit:active {box-shadow:inset 0px 2px 3px #555;}
	</style>
	
	<div id="edit_profile">
		<form action="/UBlog/UserPage" method="post">
			<input type="text" name="rfname" placeholder="First Name:" /><br />
			<input type="text" name="rlname" placeholder="Last Name:" /><br />
			<input type="text" name="rbname" placeholder="Blog's Name:" /><br />
			<input type="text" name="remail" placeholder="Email:" /><br />
			<input type="password" name="rpassword" placeholder="Password:" /><br />
			<input type="password" name="rrpassword" placeholder="Repeat Password:" /><br />
			<input type="text" name="rimage" placeholder="IMG URL:" /><br />
			<input type="hidden" name="submit" />
			<button type="submit" id="submit">Edit Profile</button>
		</form>
	</div>
	
</div>